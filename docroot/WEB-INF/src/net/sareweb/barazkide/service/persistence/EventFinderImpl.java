package net.sareweb.barazkide.service.persistence;

import java.util.ArrayList;
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

	public static String FIND_EVENTS_IN_FOLLOWED_GARDENS = EventFinder.class
			.getName() + ".findEventsInFollowedGardens";

	public List<Event> findEventsInFollowedGardens(long userId, int start,
			int end) throws SystemException {

		List<Event> events = new ArrayList<Event>();
		Session session = null;
		try {
			session = openSession();
			String sql = CustomSQLUtil.get(FIND_EVENTS_IN_FOLLOWED_GARDENS);
			SQLQuery q = session.createSQLQuery(sql);
			q.addEntity("event", EventImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);
			qPos.add(userId);

			events = (List<Event>) QueryUtil.list(q, getDialect(), start, end);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			closeSession(session);
		}
		return events;
	}

}
