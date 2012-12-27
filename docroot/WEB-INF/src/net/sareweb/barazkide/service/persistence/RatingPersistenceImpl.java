/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package net.sareweb.barazkide.service.persistence;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import net.sareweb.barazkide.NoSuchRatingException;
import net.sareweb.barazkide.model.Rating;
import net.sareweb.barazkide.model.impl.RatingImpl;
import net.sareweb.barazkide.model.impl.RatingModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the rating service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author A.Galdos
 * @see RatingPersistence
 * @see RatingUtil
 * @generated
 */
public class RatingPersistenceImpl extends BasePersistenceImpl<Rating>
	implements RatingPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link RatingUtil} to access the rating persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = RatingImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_RATEDOBJECTID =
		new FinderPath(RatingModelImpl.ENTITY_CACHE_ENABLED,
			RatingModelImpl.FINDER_CACHE_ENABLED, RatingImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByRatedObjectId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RATEDOBJECTID =
		new FinderPath(RatingModelImpl.ENTITY_CACHE_ENABLED,
			RatingModelImpl.FINDER_CACHE_ENABLED, RatingImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByRatedObjectId",
			new String[] { Long.class.getName() },
			RatingModelImpl.RATEDOBJECTID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_RATEDOBJECTID = new FinderPath(RatingModelImpl.ENTITY_CACHE_ENABLED,
			RatingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByRatedObjectId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(RatingModelImpl.ENTITY_CACHE_ENABLED,
			RatingModelImpl.FINDER_CACHE_ENABLED, RatingImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(RatingModelImpl.ENTITY_CACHE_ENABLED,
			RatingModelImpl.FINDER_CACHE_ENABLED, RatingImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(RatingModelImpl.ENTITY_CACHE_ENABLED,
			RatingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the rating in the entity cache if it is enabled.
	 *
	 * @param rating the rating
	 */
	public void cacheResult(Rating rating) {
		EntityCacheUtil.putResult(RatingModelImpl.ENTITY_CACHE_ENABLED,
			RatingImpl.class, rating.getPrimaryKey(), rating);

		rating.resetOriginalValues();
	}

	/**
	 * Caches the ratings in the entity cache if it is enabled.
	 *
	 * @param ratings the ratings
	 */
	public void cacheResult(List<Rating> ratings) {
		for (Rating rating : ratings) {
			if (EntityCacheUtil.getResult(
						RatingModelImpl.ENTITY_CACHE_ENABLED, RatingImpl.class,
						rating.getPrimaryKey()) == null) {
				cacheResult(rating);
			}
			else {
				rating.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all ratings.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(RatingImpl.class.getName());
		}

		EntityCacheUtil.clearCache(RatingImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the rating.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Rating rating) {
		EntityCacheUtil.removeResult(RatingModelImpl.ENTITY_CACHE_ENABLED,
			RatingImpl.class, rating.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<Rating> ratings) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Rating rating : ratings) {
			EntityCacheUtil.removeResult(RatingModelImpl.ENTITY_CACHE_ENABLED,
				RatingImpl.class, rating.getPrimaryKey());
		}
	}

	/**
	 * Creates a new rating with the primary key. Does not add the rating to the database.
	 *
	 * @param ratingId the primary key for the new rating
	 * @return the new rating
	 */
	public Rating create(long ratingId) {
		Rating rating = new RatingImpl();

		rating.setNew(true);
		rating.setPrimaryKey(ratingId);

		return rating;
	}

	/**
	 * Removes the rating with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ratingId the primary key of the rating
	 * @return the rating that was removed
	 * @throws net.sareweb.barazkide.NoSuchRatingException if a rating with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Rating remove(long ratingId)
		throws NoSuchRatingException, SystemException {
		return remove(Long.valueOf(ratingId));
	}

	/**
	 * Removes the rating with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the rating
	 * @return the rating that was removed
	 * @throws net.sareweb.barazkide.NoSuchRatingException if a rating with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Rating remove(Serializable primaryKey)
		throws NoSuchRatingException, SystemException {
		Session session = null;

		try {
			session = openSession();

			Rating rating = (Rating)session.get(RatingImpl.class, primaryKey);

			if (rating == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchRatingException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(rating);
		}
		catch (NoSuchRatingException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected Rating removeImpl(Rating rating) throws SystemException {
		rating = toUnwrappedModel(rating);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, rating);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(rating);

		return rating;
	}

	@Override
	public Rating updateImpl(net.sareweb.barazkide.model.Rating rating,
		boolean merge) throws SystemException {
		rating = toUnwrappedModel(rating);

		boolean isNew = rating.isNew();

		RatingModelImpl ratingModelImpl = (RatingModelImpl)rating;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, rating, merge);

			rating.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !RatingModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((ratingModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RATEDOBJECTID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(ratingModelImpl.getOriginalRatedObjectId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_RATEDOBJECTID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RATEDOBJECTID,
					args);

				args = new Object[] {
						Long.valueOf(ratingModelImpl.getRatedObjectId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_RATEDOBJECTID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RATEDOBJECTID,
					args);
			}
		}

		EntityCacheUtil.putResult(RatingModelImpl.ENTITY_CACHE_ENABLED,
			RatingImpl.class, rating.getPrimaryKey(), rating);

		return rating;
	}

	protected Rating toUnwrappedModel(Rating rating) {
		if (rating instanceof RatingImpl) {
			return rating;
		}

		RatingImpl ratingImpl = new RatingImpl();

		ratingImpl.setNew(rating.isNew());
		ratingImpl.setPrimaryKey(rating.getPrimaryKey());

		ratingImpl.setRatingId(rating.getRatingId());
		ratingImpl.setUserId(rating.getUserId());
		ratingImpl.setRateType(rating.getRateType());
		ratingImpl.setRatedObjectId(rating.getRatedObjectId());
		ratingImpl.setRatingDate(rating.getRatingDate());
		ratingImpl.setRate(rating.getRate());

		return ratingImpl;
	}

	/**
	 * Returns the rating with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the rating
	 * @return the rating
	 * @throws com.liferay.portal.NoSuchModelException if a rating with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Rating findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the rating with the primary key or throws a {@link net.sareweb.barazkide.NoSuchRatingException} if it could not be found.
	 *
	 * @param ratingId the primary key of the rating
	 * @return the rating
	 * @throws net.sareweb.barazkide.NoSuchRatingException if a rating with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Rating findByPrimaryKey(long ratingId)
		throws NoSuchRatingException, SystemException {
		Rating rating = fetchByPrimaryKey(ratingId);

		if (rating == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + ratingId);
			}

			throw new NoSuchRatingException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				ratingId);
		}

		return rating;
	}

	/**
	 * Returns the rating with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the rating
	 * @return the rating, or <code>null</code> if a rating with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Rating fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the rating with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param ratingId the primary key of the rating
	 * @return the rating, or <code>null</code> if a rating with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Rating fetchByPrimaryKey(long ratingId) throws SystemException {
		Rating rating = (Rating)EntityCacheUtil.getResult(RatingModelImpl.ENTITY_CACHE_ENABLED,
				RatingImpl.class, ratingId);

		if (rating == _nullRating) {
			return null;
		}

		if (rating == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				rating = (Rating)session.get(RatingImpl.class,
						Long.valueOf(ratingId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (rating != null) {
					cacheResult(rating);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(RatingModelImpl.ENTITY_CACHE_ENABLED,
						RatingImpl.class, ratingId, _nullRating);
				}

				closeSession(session);
			}
		}

		return rating;
	}

	/**
	 * Returns all the ratings where ratedObjectId = &#63;.
	 *
	 * @param ratedObjectId the rated object ID
	 * @return the matching ratings
	 * @throws SystemException if a system exception occurred
	 */
	public List<Rating> findByRatedObjectId(long ratedObjectId)
		throws SystemException {
		return findByRatedObjectId(ratedObjectId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ratings where ratedObjectId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param ratedObjectId the rated object ID
	 * @param start the lower bound of the range of ratings
	 * @param end the upper bound of the range of ratings (not inclusive)
	 * @return the range of matching ratings
	 * @throws SystemException if a system exception occurred
	 */
	public List<Rating> findByRatedObjectId(long ratedObjectId, int start,
		int end) throws SystemException {
		return findByRatedObjectId(ratedObjectId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ratings where ratedObjectId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param ratedObjectId the rated object ID
	 * @param start the lower bound of the range of ratings
	 * @param end the upper bound of the range of ratings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ratings
	 * @throws SystemException if a system exception occurred
	 */
	public List<Rating> findByRatedObjectId(long ratedObjectId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RATEDOBJECTID;
			finderArgs = new Object[] { ratedObjectId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_RATEDOBJECTID;
			finderArgs = new Object[] {
					ratedObjectId,
					
					start, end, orderByComparator
				};
		}

		List<Rating> list = (List<Rating>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Rating rating : list) {
				if ((ratedObjectId != rating.getRatedObjectId())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(2);
			}

			query.append(_SQL_SELECT_RATING_WHERE);

			query.append(_FINDER_COLUMN_RATEDOBJECTID_RATEDOBJECTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ratedObjectId);

				list = (List<Rating>)QueryUtil.list(q, getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first rating in the ordered set where ratedObjectId = &#63;.
	 *
	 * @param ratedObjectId the rated object ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching rating
	 * @throws net.sareweb.barazkide.NoSuchRatingException if a matching rating could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Rating findByRatedObjectId_First(long ratedObjectId,
		OrderByComparator orderByComparator)
		throws NoSuchRatingException, SystemException {
		Rating rating = fetchByRatedObjectId_First(ratedObjectId,
				orderByComparator);

		if (rating != null) {
			return rating;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ratedObjectId=");
		msg.append(ratedObjectId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRatingException(msg.toString());
	}

	/**
	 * Returns the first rating in the ordered set where ratedObjectId = &#63;.
	 *
	 * @param ratedObjectId the rated object ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching rating, or <code>null</code> if a matching rating could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Rating fetchByRatedObjectId_First(long ratedObjectId,
		OrderByComparator orderByComparator) throws SystemException {
		List<Rating> list = findByRatedObjectId(ratedObjectId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last rating in the ordered set where ratedObjectId = &#63;.
	 *
	 * @param ratedObjectId the rated object ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching rating
	 * @throws net.sareweb.barazkide.NoSuchRatingException if a matching rating could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Rating findByRatedObjectId_Last(long ratedObjectId,
		OrderByComparator orderByComparator)
		throws NoSuchRatingException, SystemException {
		Rating rating = fetchByRatedObjectId_Last(ratedObjectId,
				orderByComparator);

		if (rating != null) {
			return rating;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ratedObjectId=");
		msg.append(ratedObjectId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRatingException(msg.toString());
	}

	/**
	 * Returns the last rating in the ordered set where ratedObjectId = &#63;.
	 *
	 * @param ratedObjectId the rated object ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching rating, or <code>null</code> if a matching rating could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Rating fetchByRatedObjectId_Last(long ratedObjectId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByRatedObjectId(ratedObjectId);

		List<Rating> list = findByRatedObjectId(ratedObjectId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ratings before and after the current rating in the ordered set where ratedObjectId = &#63;.
	 *
	 * @param ratingId the primary key of the current rating
	 * @param ratedObjectId the rated object ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next rating
	 * @throws net.sareweb.barazkide.NoSuchRatingException if a rating with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Rating[] findByRatedObjectId_PrevAndNext(long ratingId,
		long ratedObjectId, OrderByComparator orderByComparator)
		throws NoSuchRatingException, SystemException {
		Rating rating = findByPrimaryKey(ratingId);

		Session session = null;

		try {
			session = openSession();

			Rating[] array = new RatingImpl[3];

			array[0] = getByRatedObjectId_PrevAndNext(session, rating,
					ratedObjectId, orderByComparator, true);

			array[1] = rating;

			array[2] = getByRatedObjectId_PrevAndNext(session, rating,
					ratedObjectId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Rating getByRatedObjectId_PrevAndNext(Session session,
		Rating rating, long ratedObjectId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_RATING_WHERE);

		query.append(_FINDER_COLUMN_RATEDOBJECTID_RATEDOBJECTID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(ratedObjectId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(rating);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Rating> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the ratings.
	 *
	 * @return the ratings
	 * @throws SystemException if a system exception occurred
	 */
	public List<Rating> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ratings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of ratings
	 * @param end the upper bound of the range of ratings (not inclusive)
	 * @return the range of ratings
	 * @throws SystemException if a system exception occurred
	 */
	public List<Rating> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the ratings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of ratings
	 * @param end the upper bound of the range of ratings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ratings
	 * @throws SystemException if a system exception occurred
	 */
	public List<Rating> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = new Object[] { start, end, orderByComparator };

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<Rating> list = (List<Rating>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_RATING);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_RATING;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<Rating>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);
				}
				else {
					list = (List<Rating>)QueryUtil.list(q, getDialect(), start,
							end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the ratings where ratedObjectId = &#63; from the database.
	 *
	 * @param ratedObjectId the rated object ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByRatedObjectId(long ratedObjectId)
		throws SystemException {
		for (Rating rating : findByRatedObjectId(ratedObjectId)) {
			remove(rating);
		}
	}

	/**
	 * Removes all the ratings from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (Rating rating : findAll()) {
			remove(rating);
		}
	}

	/**
	 * Returns the number of ratings where ratedObjectId = &#63;.
	 *
	 * @param ratedObjectId the rated object ID
	 * @return the number of matching ratings
	 * @throws SystemException if a system exception occurred
	 */
	public int countByRatedObjectId(long ratedObjectId)
		throws SystemException {
		Object[] finderArgs = new Object[] { ratedObjectId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_RATEDOBJECTID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_RATING_WHERE);

			query.append(_FINDER_COLUMN_RATEDOBJECTID_RATEDOBJECTID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ratedObjectId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_RATEDOBJECTID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of ratings.
	 *
	 * @return the number of ratings
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_RATING);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Initializes the rating persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.net.sareweb.barazkide.model.Rating")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<Rating>> listenersList = new ArrayList<ModelListener<Rating>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<Rating>)InstanceFactory.newInstance(
							listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	public void destroy() {
		EntityCacheUtil.removeCache(RatingImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@BeanReference(type = BadgePersistence.class)
	protected BadgePersistence badgePersistence;
	@BeanReference(type = EventPersistence.class)
	protected EventPersistence eventPersistence;
	@BeanReference(type = FollowingPersistence.class)
	protected FollowingPersistence followingPersistence;
	@BeanReference(type = GardenPersistence.class)
	protected GardenPersistence gardenPersistence;
	@BeanReference(type = GardenBadgePersistence.class)
	protected GardenBadgePersistence gardenBadgePersistence;
	@BeanReference(type = GardenImagePersistence.class)
	protected GardenImagePersistence gardenImagePersistence;
	@BeanReference(type = MembershipPersistence.class)
	protected MembershipPersistence membershipPersistence;
	@BeanReference(type = RatingPersistence.class)
	protected RatingPersistence ratingPersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_RATING = "SELECT rating FROM Rating rating";
	private static final String _SQL_SELECT_RATING_WHERE = "SELECT rating FROM Rating rating WHERE ";
	private static final String _SQL_COUNT_RATING = "SELECT COUNT(rating) FROM Rating rating";
	private static final String _SQL_COUNT_RATING_WHERE = "SELECT COUNT(rating) FROM Rating rating WHERE ";
	private static final String _FINDER_COLUMN_RATEDOBJECTID_RATEDOBJECTID_2 = "rating.ratedObjectId = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "rating.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Rating exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Rating exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(RatingPersistenceImpl.class);
	private static Rating _nullRating = new RatingImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<Rating> toCacheModel() {
				return _nullRatingCacheModel;
			}
		};

	private static CacheModel<Rating> _nullRatingCacheModel = new CacheModel<Rating>() {
			public Rating toEntityModel() {
				return _nullRating;
			}
		};
}