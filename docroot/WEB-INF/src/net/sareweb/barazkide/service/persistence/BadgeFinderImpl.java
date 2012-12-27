package net.sareweb.barazkide.service.persistence;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sareweb.barazkide.model.Badge;
import net.sareweb.barazkide.model.impl.BadgeImpl;


import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

public class BadgeFinderImpl extends BasePersistenceImpl<Badge> implements
		BadgeFinder {

	public static String GET_GARDEN_BADGES = BadgeFinder.class
			.getName() + ".getGardenBadges";


	public List<Badge> getGardenBadges(long gardenId) throws SystemException {

		List<Badge> badges = new ArrayList<Badge>();
		Session session = null;
		try {
			session = openSession();
			String sql = CustomSQLUtil.get(GET_GARDEN_BADGES);
			SQLQuery q = session.createSQLQuery(sql);
			q.addEntity("badge", BadgeImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);
			qPos.add(gardenId);

			badges = (List<Badge>) QueryUtil.list(q, getDialect(), -1, -1);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			closeSession(session);
		}
		return badges;
	}
	
	
}
