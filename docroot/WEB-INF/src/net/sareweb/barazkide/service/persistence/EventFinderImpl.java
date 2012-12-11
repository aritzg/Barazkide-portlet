package net.sareweb.barazkide.service.persistence;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sareweb.barazkide.model.Event;
import net.sareweb.barazkide.model.impl.EventImpl;

import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

public class EventFinderImpl extends BasePersistenceImpl<Event> implements
		EventFinder {

	public static String FIND_EVENTS_IN_FOLLOWED_GARDENS_OLDER = EventFinder.class
			.getName() + ".findEventsInFollowedGardensOlderThanDate";
	public static String FIND_EVENTS_IN_FOLLOWED_GARDENS_NEWER = EventFinder.class
			.getName() + ".findEventsInFollowedGardensNewerThanDate";

	public List<Event> findEventsInFollowedGardensOlderThanDate(long userId, long followingDate, int blockSize) throws SystemException {

		List<Event> events = new ArrayList<Event>();
		Session session = null;
		try {
			session = openSession();
			String sql = CustomSQLUtil.get(FIND_EVENTS_IN_FOLLOWED_GARDENS_OLDER);
			SQLQuery q = session.createSQLQuery(sql);
			q.addEntity("event", EventImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);
			qPos.add(userId);
			qPos.add(new Date(followingDate));

			events = (List<Event>) QueryUtil.list(q, getDialect(), 0, blockSize);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			closeSession(session);
		}
		return events;
	}
	
	public List<Event> findEventsInFollowedGardensNewerThanDate(long userId, long followingDate, int blockSize) throws SystemException {

		List<Event> events = new ArrayList<Event>();
		Session session = null;
		try {
			session = openSession();
			String sql = CustomSQLUtil.get(FIND_EVENTS_IN_FOLLOWED_GARDENS_NEWER);
			SQLQuery q = session.createSQLQuery(sql);
			q.addEntity("event", EventImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);
			qPos.add(userId);
			qPos.add(new Date(followingDate));

			events = (List<Event>) QueryUtil.list(q, getDialect(), 0, blockSize);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			closeSession(session);
		}
		return events;
	}

}
