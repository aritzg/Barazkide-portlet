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

import net.sareweb.barazkide.NoSuchGardenException;
import net.sareweb.barazkide.model.Garden;
import net.sareweb.barazkide.model.impl.GardenImpl;
import net.sareweb.barazkide.model.impl.GardenModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the garden service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author A.Galdos
 * @see GardenPersistence
 * @see GardenUtil
 * @generated
 */
public class GardenPersistenceImpl extends BasePersistenceImpl<Garden>
	implements GardenPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link GardenUtil} to access the garden persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = GardenImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(GardenModelImpl.ENTITY_CACHE_ENABLED,
			GardenModelImpl.FINDER_CACHE_ENABLED, GardenImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(GardenModelImpl.ENTITY_CACHE_ENABLED,
			GardenModelImpl.FINDER_CACHE_ENABLED, GardenImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(GardenModelImpl.ENTITY_CACHE_ENABLED,
			GardenModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the garden in the entity cache if it is enabled.
	 *
	 * @param garden the garden
	 */
	public void cacheResult(Garden garden) {
		EntityCacheUtil.putResult(GardenModelImpl.ENTITY_CACHE_ENABLED,
			GardenImpl.class, garden.getPrimaryKey(), garden);

		garden.resetOriginalValues();
	}

	/**
	 * Caches the gardens in the entity cache if it is enabled.
	 *
	 * @param gardens the gardens
	 */
	public void cacheResult(List<Garden> gardens) {
		for (Garden garden : gardens) {
			if (EntityCacheUtil.getResult(
						GardenModelImpl.ENTITY_CACHE_ENABLED, GardenImpl.class,
						garden.getPrimaryKey()) == null) {
				cacheResult(garden);
			}
			else {
				garden.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all gardens.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(GardenImpl.class.getName());
		}

		EntityCacheUtil.clearCache(GardenImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the garden.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Garden garden) {
		EntityCacheUtil.removeResult(GardenModelImpl.ENTITY_CACHE_ENABLED,
			GardenImpl.class, garden.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<Garden> gardens) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Garden garden : gardens) {
			EntityCacheUtil.removeResult(GardenModelImpl.ENTITY_CACHE_ENABLED,
				GardenImpl.class, garden.getPrimaryKey());
		}
	}

	/**
	 * Creates a new garden with the primary key. Does not add the garden to the database.
	 *
	 * @param gardenId the primary key for the new garden
	 * @return the new garden
	 */
	public Garden create(long gardenId) {
		Garden garden = new GardenImpl();

		garden.setNew(true);
		garden.setPrimaryKey(gardenId);

		return garden;
	}

	/**
	 * Removes the garden with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param gardenId the primary key of the garden
	 * @return the garden that was removed
	 * @throws net.sareweb.barazkide.NoSuchGardenException if a garden with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Garden remove(long gardenId)
		throws NoSuchGardenException, SystemException {
		return remove(Long.valueOf(gardenId));
	}

	/**
	 * Removes the garden with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the garden
	 * @return the garden that was removed
	 * @throws net.sareweb.barazkide.NoSuchGardenException if a garden with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Garden remove(Serializable primaryKey)
		throws NoSuchGardenException, SystemException {
		Session session = null;

		try {
			session = openSession();

			Garden garden = (Garden)session.get(GardenImpl.class, primaryKey);

			if (garden == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchGardenException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(garden);
		}
		catch (NoSuchGardenException nsee) {
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
	protected Garden removeImpl(Garden garden) throws SystemException {
		garden = toUnwrappedModel(garden);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, garden);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(garden);

		return garden;
	}

	@Override
	public Garden updateImpl(net.sareweb.barazkide.model.Garden garden,
		boolean merge) throws SystemException {
		garden = toUnwrappedModel(garden);

		boolean isNew = garden.isNew();

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, garden, merge);

			garden.setNew(false);
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

		EntityCacheUtil.putResult(GardenModelImpl.ENTITY_CACHE_ENABLED,
			GardenImpl.class, garden.getPrimaryKey(), garden);

		return garden;
	}

	protected Garden toUnwrappedModel(Garden garden) {
		if (garden instanceof GardenImpl) {
			return garden;
		}

		GardenImpl gardenImpl = new GardenImpl();

		gardenImpl.setNew(garden.isNew());
		gardenImpl.setPrimaryKey(garden.getPrimaryKey());

		gardenImpl.setGardenId(garden.getGardenId());
		gardenImpl.setGardenImageId(garden.getGardenImageId());
		gardenImpl.setOwnerUserId(garden.getOwnerUserId());
		gardenImpl.setCreateDate(garden.getCreateDate());
		gardenImpl.setModifiedDate(garden.getModifiedDate());
		gardenImpl.setName(garden.getName());
		gardenImpl.setComment(garden.getComment());
		gardenImpl.setLat(garden.getLat());
		gardenImpl.setLng(garden.getLng());

		return gardenImpl;
	}

	/**
	 * Returns the garden with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the garden
	 * @return the garden
	 * @throws com.liferay.portal.NoSuchModelException if a garden with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Garden findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the garden with the primary key or throws a {@link net.sareweb.barazkide.NoSuchGardenException} if it could not be found.
	 *
	 * @param gardenId the primary key of the garden
	 * @return the garden
	 * @throws net.sareweb.barazkide.NoSuchGardenException if a garden with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Garden findByPrimaryKey(long gardenId)
		throws NoSuchGardenException, SystemException {
		Garden garden = fetchByPrimaryKey(gardenId);

		if (garden == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + gardenId);
			}

			throw new NoSuchGardenException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				gardenId);
		}

		return garden;
	}

	/**
	 * Returns the garden with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the garden
	 * @return the garden, or <code>null</code> if a garden with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Garden fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the garden with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param gardenId the primary key of the garden
	 * @return the garden, or <code>null</code> if a garden with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Garden fetchByPrimaryKey(long gardenId) throws SystemException {
		Garden garden = (Garden)EntityCacheUtil.getResult(GardenModelImpl.ENTITY_CACHE_ENABLED,
				GardenImpl.class, gardenId);

		if (garden == _nullGarden) {
			return null;
		}

		if (garden == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				garden = (Garden)session.get(GardenImpl.class,
						Long.valueOf(gardenId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (garden != null) {
					cacheResult(garden);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(GardenModelImpl.ENTITY_CACHE_ENABLED,
						GardenImpl.class, gardenId, _nullGarden);
				}

				closeSession(session);
			}
		}

		return garden;
	}

	/**
	 * Returns all the gardens.
	 *
	 * @return the gardens
	 * @throws SystemException if a system exception occurred
	 */
	public List<Garden> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the gardens.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of gardens
	 * @param end the upper bound of the range of gardens (not inclusive)
	 * @return the range of gardens
	 * @throws SystemException if a system exception occurred
	 */
	public List<Garden> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the gardens.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of gardens
	 * @param end the upper bound of the range of gardens (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of gardens
	 * @throws SystemException if a system exception occurred
	 */
	public List<Garden> findAll(int start, int end,
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

		List<Garden> list = (List<Garden>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_GARDEN);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_GARDEN.concat(GardenModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<Garden>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);
				}
				else {
					list = (List<Garden>)QueryUtil.list(q, getDialect(), start,
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
	 * Removes all the gardens from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (Garden garden : findAll()) {
			remove(garden);
		}
	}

	/**
	 * Returns the number of gardens.
	 *
	 * @return the number of gardens
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_GARDEN);

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
	 * Initializes the garden persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.net.sareweb.barazkide.model.Garden")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<Garden>> listenersList = new ArrayList<ModelListener<Garden>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<Garden>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(GardenImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@BeanReference(type = EventPersistence.class)
	protected EventPersistence eventPersistence;
	@BeanReference(type = FollowingPersistence.class)
	protected FollowingPersistence followingPersistence;
	@BeanReference(type = GardenPersistence.class)
	protected GardenPersistence gardenPersistence;
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
	private static final String _SQL_SELECT_GARDEN = "SELECT garden FROM Garden garden";
	private static final String _SQL_COUNT_GARDEN = "SELECT COUNT(garden) FROM Garden garden";
	private static final String _ORDER_BY_ENTITY_ALIAS = "garden.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Garden exists with the primary key ";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(GardenPersistenceImpl.class);
	private static Garden _nullGarden = new GardenImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<Garden> toCacheModel() {
				return _nullGardenCacheModel;
			}
		};

	private static CacheModel<Garden> _nullGardenCacheModel = new CacheModel<Garden>() {
			public Garden toEntityModel() {
				return _nullGarden;
			}
		};
}