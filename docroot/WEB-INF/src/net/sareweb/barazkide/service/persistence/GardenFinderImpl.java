package net.sareweb.barazkide.service.persistence;

import java.util.ArrayList;
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
	
	public static String FIND_FOLLOWED_GARDENS = GardenFinder.class
			.getName() + ".findFollowedGardens";
	
	public List<Garden> findFollowedGardens(long userId, int start,
			int end) throws SystemException {

		List<Garden> gardens = new ArrayList<Garden>();
		Session session = null;
		try {
			session = openSession();
			String sql = CustomSQLUtil.get(FIND_FOLLOWED_GARDENS);
			SQLQuery q = session.createSQLQuery(sql);
			q.addEntity("garden", GardenImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);
			qPos.add(userId);

			gardens = (List<Garden>) QueryUtil.list(q, getDialect(), start, end);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			closeSession(session);
		}
		return gardens;
	}
}