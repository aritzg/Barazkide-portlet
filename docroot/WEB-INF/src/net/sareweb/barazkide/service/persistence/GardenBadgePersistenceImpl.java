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
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import net.sareweb.barazkide.NoSuchGardenBadgeException;
import net.sareweb.barazkide.model.GardenBadge;
import net.sareweb.barazkide.model.impl.GardenBadgeImpl;
import net.sareweb.barazkide.model.impl.GardenBadgeModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the garden badge service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author A.Galdos
 * @see GardenBadgePersistence
 * @see GardenBadgeUtil
 * @generated
 */
public class GardenBadgePersistenceImpl extends BasePersistenceImpl<GardenBadge>
	implements GardenBadgePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link GardenBadgeUtil} to access the garden badge persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = GardenBadgeImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(GardenBadgeModelImpl.ENTITY_CACHE_ENABLED,
			GardenBadgeModelImpl.FINDER_CACHE_ENABLED, GardenBadgeImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(GardenBadgeModelImpl.ENTITY_CACHE_ENABLED,
			GardenBadgeModelImpl.FINDER_CACHE_ENABLED, GardenBadgeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(GardenBadgeModelImpl.ENTITY_CACHE_ENABLED,
			GardenBadgeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the garden badge in the entity cache if it is enabled.
	 *
	 * @param gardenBadge the garden badge
	 */
	public void cacheResult(GardenBadge gardenBadge) {
		EntityCacheUtil.putResult(GardenBadgeModelImpl.ENTITY_CACHE_ENABLED,
			GardenBadgeImpl.class, gardenBadge.getPrimaryKey(), gardenBadge);

		gardenBadge.resetOriginalValues();
	}

	/**
	 * Caches the garden badges in the entity cache if it is enabled.
	 *
	 * @param gardenBadges the garden badges
	 */
	public void cacheResult(List<GardenBadge> gardenBadges) {
		for (GardenBadge gardenBadge : gardenBadges) {
			if (EntityCacheUtil.getResult(
						GardenBadgeModelImpl.ENTITY_CACHE_ENABLED,
						GardenBadgeImpl.class, gardenBadge.getPrimaryKey()) == null) {
				cacheResult(gardenBadge);
			}
			else {
				gardenBadge.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all garden badges.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(GardenBadgeImpl.class.getName());
		}

		EntityCacheUtil.clearCache(GardenBadgeImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the garden badge.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(GardenBadge gardenBadge) {
		EntityCacheUtil.removeResult(GardenBadgeModelImpl.ENTITY_CACHE_ENABLED,
			GardenBadgeImpl.class, gardenBadge.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<GardenBadge> gardenBadges) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (GardenBadge gardenBadge : gardenBadges) {
			EntityCacheUtil.removeResult(GardenBadgeModelImpl.ENTITY_CACHE_ENABLED,
				GardenBadgeImpl.class, gardenBadge.getPrimaryKey());
		}
	}

	/**
	 * Creates a new garden badge with the primary key. Does not add the garden badge to the database.
	 *
	 * @param gardenBadgeId the primary key for the new garden badge
	 * @return the new garden badge
	 */
	public GardenBadge create(long gardenBadgeId) {
		GardenBadge gardenBadge = new GardenBadgeImpl();

		gardenBadge.setNew(true);
		gardenBadge.setPrimaryKey(gardenBadgeId);

		return gardenBadge;
	}

	/**
	 * Removes the garden badge with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param gardenBadgeId the primary key of the garden badge
	 * @return the garden badge that was removed
	 * @throws net.sareweb.barazkide.NoSuchGardenBadgeException if a garden badge with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public GardenBadge remove(long gardenBadgeId)
		throws NoSuchGardenBadgeException, SystemException {
		return remove(Long.valueOf(gardenBadgeId));
	}

	/**
	 * Removes the garden badge with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the garden badge
	 * @return the garden badge that was removed
	 * @throws net.sareweb.barazkide.NoSuchGardenBadgeException if a garden badge with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public GardenBadge remove(Serializable primaryKey)
		throws NoSuchGardenBadgeException, SystemException {
		Session session = null;

		try {
			session = openSession();

			GardenBadge gardenBadge = (GardenBadge)session.get(GardenBadgeImpl.class,
					primaryKey);

			if (gardenBadge == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchGardenBadgeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(gardenBadge);
		}
		catch (NoSuchGardenBadgeException nsee) {
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
	protected GardenBadge removeImpl(GardenBadge gardenBadge)
		throws SystemException {
		gardenBadge = toUnwrappedModel(gardenBadge);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, gardenBadge);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(gardenBadge);

		return gardenBadge;
	}

	@Override
	public GardenBadge updateImpl(
		net.sareweb.barazkide.model.GardenBadge gardenBadge, boolean merge)
		throws SystemException {
		gardenBadge = toUnwrappedModel(gardenBadge);

		boolean isNew = gardenBadge.isNew();

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, gardenBadge, merge);

			gardenBadge.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		EntityCacheUtil.putResult(GardenBadgeModelImpl.ENTITY_CACHE_ENABLED,
			GardenBadgeImpl.class, gardenBadge.getPrimaryKey(), gardenBadge);

		return gardenBadge;
	}

	protected GardenBadge toUnwrappedModel(GardenBadge gardenBadge) {
		if (gardenBadge instanceof GardenBadgeImpl) {
			return gardenBadge;
		}

		GardenBadgeImpl gardenBadgeImpl = new GardenBadgeImpl();

		gardenBadgeImpl.setNew(gardenBadge.isNew());
		gardenBadgeImpl.setPrimaryKey(gardenBadge.getPrimaryKey());

		gardenBadgeImpl.setGardenBadgeId(gardenBadge.getGardenBadgeId());
		gardenBadgeImpl.setGardenId(gardenBadge.getGardenId());
		gardenBadgeImpl.setBadgeId(gardenBadge.getBadgeId());
		gardenBadgeImpl.setObtainedDate(gardenBadge.getObtainedDate());

		return gardenBadgeImpl;
	}

	/**
	 * Returns the garden badge with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the garden badge
	 * @return the garden badge
	 * @throws com.liferay.portal.NoSuchModelException if a garden badge with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public GardenBadge findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the garden badge with the primary key or throws a {@link net.sareweb.barazkide.NoSuchGardenBadgeException} if it could not be found.
	 *
	 * @param gardenBadgeId the primary key of the garden badge
	 * @return the garden badge
	 * @throws net.sareweb.barazkide.NoSuchGardenBadgeException if a garden badge with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public GardenBadge findByPrimaryKey(long gardenBadgeId)
		throws NoSuchGardenBadgeException, SystemException {
		GardenBadge gardenBadge = fetchByPrimaryKey(gardenBadgeId);

		if (gardenBadge == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + gardenBadgeId);
			}

			throw new NoSuchGardenBadgeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				gardenBadgeId);
		}

		return gardenBadge;
	}

	/**
	 * Returns the garden badge with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the garden badge
	 * @return the garden badge, or <code>null</code> if a garden badge with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public GardenBadge fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the garden badge with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param gardenBadgeId the primary key of the garden badge
	 * @return the garden badge, or <code>null</code> if a garden badge with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public GardenBadge fetchByPrimaryKey(long gardenBadgeId)
		throws SystemException {
		GardenBadge gardenBadge = (GardenBadge)EntityCacheUtil.getResult(GardenBadgeModelImpl.ENTITY_CACHE_ENABLED,
				GardenBadgeImpl.class, gardenBadgeId);

		if (gardenBadge == _nullGardenBadge) {
			return null;
		}

		if (gardenBadge == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				gardenBadge = (GardenBadge)session.get(GardenBadgeImpl.class,
						Long.valueOf(gardenBadgeId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (gardenBadge != null) {
					cacheResult(gardenBadge);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(GardenBadgeModelImpl.ENTITY_CACHE_ENABLED,
						GardenBadgeImpl.class, gardenBadgeId, _nullGardenBadge);
				}

				closeSession(session);
			}
		}

		return gardenBadge;
	}

	/**
	 * Returns all the garden badges.
	 *
	 * @return the garden badges
	 * @throws SystemException if a system exception occurred
	 */
	public List<GardenBadge> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the garden badges.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of garden badges
	 * @param end the upper bound of the range of garden badges (not inclusive)
	 * @return the range of garden badges
	 * @throws SystemException if a system exception occurred
	 */
	public List<GardenBadge> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the garden badges.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of garden badges
	 * @param end the upper bound of the range of garden badges (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of garden badges
	 * @throws SystemException if a system exception occurred
	 */
	public List<GardenBadge> findAll(int start, int end,
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

		List<GardenBadge> list = (List<GardenBadge>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_GARDENBADGE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_GARDENBADGE;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<GardenBadge>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<GardenBadge>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Removes all the garden badges from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (GardenBadge gardenBadge : findAll()) {
			remove(gardenBadge);
		}
	}

	/**
	 * Returns the number of garden badges.
	 *
	 * @return the number of garden badges
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_GARDENBADGE);

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
	 * Initializes the garden badge persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.net.sareweb.barazkide.model.GardenBadge")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<GardenBadge>> listenersList = new ArrayList<ModelListener<GardenBadge>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<GardenBadge>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(GardenBadgeImpl.class.getName());
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
	private static final String _SQL_SELECT_GARDENBADGE = "SELECT gardenBadge FROM GardenBadge gardenBadge";
	private static final String _SQL_COUNT_GARDENBADGE = "SELECT COUNT(gardenBadge) FROM GardenBadge gardenBadge";
	private static final String _ORDER_BY_ENTITY_ALIAS = "gardenBadge.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No GardenBadge exists with the primary key ";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(GardenBadgePersistenceImpl.class);
	private static GardenBadge _nullGardenBadge = new GardenBadgeImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<GardenBadge> toCacheModel() {
				return _nullGardenBadgeCacheModel;
			}
		};

	private static CacheModel<GardenBadge> _nullGardenBadgeCacheModel = new CacheModel<GardenBadge>() {
			public GardenBadge toEntityModel() {
				return _nullGardenBadge;
			}
		};
}