package net.sareweb.barazkide.service.persistence;

import java.util.List;

import net.sareweb.barazkide.model.Rating;
import net.sareweb.barazkide.model.impl.RatingImpl;

import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

public class RatingFinderImpl extends BasePersistenceImpl<Rating> implements
		RatingFinder {

	public static String AVG_RATING = RatingFinder.class
			.getName() + ".getAvgRating";

	public double getAvgRating(long ratedObjectId) throws SystemException {

		Session session = null;
		try {
			session = openSession();
			String sql = CustomSQLUtil.get(AVG_RATING);
			SQLQuery q = session.createSQLQuery(sql);
			q.addEntity("event", RatingImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);
			qPos.add(ratedObjectId);

			List<Double> avg = (List<Double>) QueryUtil.list(q, getDialect(), 0, 1);
			if(avg==null || avg.size()<1) return 0;
			else return avg.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			closeSession(session);
		}
	}
	
}
