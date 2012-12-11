package net.sareweb.barazkide.service.persistence;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sareweb.barazkide.model.Garden;
import net.sareweb.barazkide.model.impl.GardenImpl;

import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

public class GardenFinderImpl extends BasePersistenceImpl<Garden> implements GardenFinder {
	
	public static String FIND_FOLLOWED_GARDENS_OLDER = GardenFinder.class
			.getName() + ".findFollowedGardensOlderThanDate";
	public static String FIND_FOLLOWED_GARDENS_NEWER = GardenFinder.class
			.getName() + ".findFollowedGardensNewerThanDate";
	public static String FIND_PARTICIPATING_GARDENS_OLDER = GardenFinder.class
			.getName() + ".findParticipatingGardensOlderThanDate";
	public static String FIND_PARTICIPATING_GARDENS_NEWER = GardenFinder.class
			.getName() + ".findParticipatingGardensNewerThanDate";
	
	public List<Garden> findFollowedGardensOlderThanDate(long userId, long followingDate, int blockSize) throws SystemException {

		List<Garden> gardens = new ArrayList<Garden>();
		Session session = null;
		try {
			session = openSession();
			String sql = CustomSQLUtil.get(FIND_FOLLOWED_GARDENS_OLDER);
			SQLQuery q = session.createSQLQuery(sql);
			q.addEntity("garden", GardenImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);
			qPos.add(userId);
			qPos.add(new Date(followingDate));

			gardens = (List<Garden>) QueryUtil.list(q, getDialect(), 0, blockSize);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			closeSession(session);
		}
		return gardens;
	}
	
	public List<Garden> findFollowedGardensNewerThanDate(long userId, long participatingDate, int blockSize) throws SystemException {

		List<Garden> gardens = new ArrayList<Garden>();
		Session session = null;
		try {
			session = openSession();
			String sql = CustomSQLUtil.get(FIND_FOLLOWED_GARDENS_NEWER);
			SQLQuery q = session.createSQLQuery(sql);
			q.addEntity("garden", GardenImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);
			qPos.add(userId);
			qPos.add(new Date(participatingDate));

			gardens = (List<Garden>) QueryUtil.list(q, getDialect(), 0, blockSize);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			closeSession(session);
		}
		return gardens;
	}
	
	public List<Garden> findParticipatingGardensOlderThanDate(long userId, long participatingDate, int blockSize) throws SystemException {

		List<Garden> gardens = new ArrayList<Garden>();
		Session session = null;
		try {
			session = openSession();
			String sql = CustomSQLUtil.get(FIND_PARTICIPATING_GARDENS_OLDER);
			SQLQuery q = session.createSQLQuery(sql);
			q.addEntity("garden", GardenImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);
			qPos.add(userId);
			qPos.add(new Date(participatingDate));

			gardens = (List<Garden>) QueryUtil.list(q, getDialect(), 0, blockSize);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			closeSession(session);
		}
		return gardens;
	}
	
	public List<Garden> findParticipatingGardensNewerThanDate(long userId, long followingDate, int blockSize) throws SystemException {

		List<Garden> gardens = new ArrayList<Garden>();
		Session session = null;
		try {
			session = openSession();
			String sql = CustomSQLUtil.get(FIND_PARTICIPATING_GARDENS_NEWER);
			SQLQuery q = session.createSQLQuery(sql);
			q.addEntity("garden", GardenImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);
			qPos.add(userId);
			qPos.add(new Date(followingDate));

			gardens = (List<Garden>) QueryUtil.list(q, getDialect(), 0, blockSize);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			closeSession(session);
		}
		return gardens;
	}
}