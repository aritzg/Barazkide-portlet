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

import net.sareweb.barazkide.NoSuchFollowingException;
import net.sareweb.barazkide.model.Following;
import net.sareweb.barazkide.model.impl.FollowingImpl;
import net.sareweb.barazkide.model.impl.FollowingModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the following service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author A.Galdos
 * @see FollowingPersistence
 * @see FollowingUtil
 * @generated
 */
public class FollowingPersistenceImpl extends BasePersistenceImpl<Following>
	implements FollowingPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link FollowingUtil} to access the following persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = FollowingImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(FollowingModelImpl.ENTITY_CACHE_ENABLED,
			FollowingModelImpl.FINDER_CACHE_ENABLED, FollowingImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(FollowingModelImpl.ENTITY_CACHE_ENABLED,
			FollowingModelImpl.FINDER_CACHE_ENABLED, FollowingImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(FollowingModelImpl.ENTITY_CACHE_ENABLED,
			FollowingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the following in the entity cache if it is enabled.
	 *
	 * @param following the following
	 */
	public void cacheResult(Following following) {
		EntityCacheUtil.putResult(FollowingModelImpl.ENTITY_CACHE_ENABLED,
			FollowingImpl.class, following.getPrimaryKey(), following);

		following.resetOriginalValues();
	}

	/**
	 * Caches the followings in the entity cache if it is enabled.
	 *
	 * @param followings the followings
	 */
	public void cacheResult(List<Following> followings) {
		for (Following following : followings) {
			if (EntityCacheUtil.getResult(
						FollowingModelImpl.ENTITY_CACHE_ENABLED,
						FollowingImpl.class, following.getPrimaryKey()) == null) {
				cacheResult(following);
			}
			else {
				following.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all followings.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(FollowingImpl.class.getName());
		}

		EntityCacheUtil.clearCache(FollowingImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the following.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Following following) {
		EntityCacheUtil.removeResult(FollowingModelImpl.ENTITY_CACHE_ENABLED,
			FollowingImpl.class, following.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<Following> followings) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Following following : followings) {
			EntityCacheUtil.removeResult(FollowingModelImpl.ENTITY_CACHE_ENABLED,
				FollowingImpl.class, following.getPrimaryKey());
		}
	}

	/**
	 * Creates a new following with the primary key. Does not add the following to the database.
	 *
	 * @param followingId the primary key for the new following
	 * @return the new following
	 */
	public Following create(long followingId) {
		Following following = new FollowingImpl();

		following.setNew(true);
		following.setPrimaryKey(followingId);

		return following;
	}

	/**
	 * Removes the following with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param followingId the primary key of the following
	 * @return the following that was removed
	 * @throws net.sareweb.barazkide.NoSuchFollowingException if a following with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Following remove(long followingId)
		throws NoSuchFollowingException, SystemException {
		return remove(Long.valueOf(followingId));
	}

	/**
	 * Removes the following with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the following
	 * @return the following that was removed
	 * @throws net.sareweb.barazkide.NoSuchFollowingException if a following with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Following remove(Serializable primaryKey)
		throws NoSuchFollowingException, SystemException {
		Session session = null;

		try {
			session = openSession();

			Following following = (Following)session.get(FollowingImpl.class,
					primaryKey);

			if (following == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchFollowingException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(following);
		}
		catch (NoSuchFollowingException nsee) {
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
	protected Following removeImpl(Following following)
		throws SystemException {
		following = toUnwrappedModel(following);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, following);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(following);

		return following;
	}

	@Override
	public Following updateImpl(
		net.sareweb.barazkide.model.Following following, boolean merge)
		throws SystemException {
		following = toUnwrappedModel(following);

		boolean isNew = following.isNew();

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, following, merge);

			following.setNew(false);
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

		EntityCacheUtil.putResult(FollowingModelImpl.ENTITY_CACHE_ENABLED,
			FollowingImpl.class, following.getPrimaryKey(), following);

		return following;
	}

	protected Following toUnwrappedModel(Following following) {
		if (following instanceof FollowingImpl) {
			return following;
		}

		FollowingImpl followingImpl = new FollowingImpl();

		followingImpl.setNew(following.isNew());
		followingImpl.setPrimaryKey(following.getPrimaryKey());

		followingImpl.setFollowingId(following.getFollowingId());
		followingImpl.setUserId(following.getUserId());
		followingImpl.setGardenId(following.getGardenId());

		return followingImpl;
	}

	/**
	 * Returns the following with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the following
	 * @return the following
	 * @throws com.liferay.portal.NoSuchModelException if a following with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Following findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the following with the primary key or throws a {@link net.sareweb.barazkide.NoSuchFollowingException} if it could not be found.
	 *
	 * @param followingId the primary key of the following
	 * @return the following
	 * @throws net.sareweb.barazkide.NoSuchFollowingException if a following with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Following findByPrimaryKey(long followingId)
		throws NoSuchFollowingException, SystemException {
		Following following = fetchByPrimaryKey(followingId);

		if (following == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + followingId);
			}

			throw new NoSuchFollowingException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				followingId);
		}

		return following;
	}

	/**
	 * Returns the following with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the following
	 * @return the following, or <code>null</code> if a following with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Following fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the following with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param followingId the primary key of the following
	 * @return the following, or <code>null</code> if a following with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Following fetchByPrimaryKey(long followingId)
		throws SystemException {
		Following following = (Following)EntityCacheUtil.getResult(FollowingModelImpl.ENTITY_CACHE_ENABLED,
				FollowingImpl.class, followingId);

		if (following == _nullFollowing) {
			return null;
		}

		if (following == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				following = (Following)session.get(FollowingImpl.class,
						Long.valueOf(followingId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (following != null) {
					cacheResult(following);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(FollowingModelImpl.ENTITY_CACHE_ENABLED,
						FollowingImpl.class, followingId, _nullFollowing);
				}

				closeSession(session);
			}
		}

		return following;
	}

	/**
	 * Returns all the followings.
	 *
	 * @return the followings
	 * @throws SystemException if a system exception occurred
	 */
	public List<Following> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the followings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of followings
	 * @param end the upper bound of the range of followings (not inclusive)
	 * @return the range of followings
	 * @throws SystemException if a system exception occurred
	 */
	public List<Following> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the followings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of followings
	 * @param end the upper bound of the range of followings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of followings
	 * @throws SystemException if a system exception occurred
	 */
	public List<Following> findAll(int start, int end,
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

		List<Following> list = (List<Following>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_FOLLOWING);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_FOLLOWING;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<Following>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<Following>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the followings from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (Following following : findAll()) {
			remove(following);
		}
	}

	/**
	 * Returns the number of followings.
	 *
	 * @return the number of followings
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_FOLLOWING);

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
	 * Initializes the following persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.net.sareweb.barazkide.model.Following")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<Following>> listenersList = new ArrayList<ModelListener<Following>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<Following>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(FollowingImpl.class.getName());
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
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_FOLLOWING = "SELECT following FROM Following following";
	private static final String _SQL_COUNT_FOLLOWING = "SELECT COUNT(following) FROM Following following";
	private static final String _ORDER_BY_ENTITY_ALIAS = "following.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Following exists with the primary key ";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(FollowingPersistenceImpl.class);
	private static Following _nullFollowing = new FollowingImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<Following> toCacheModel() {
				return _nullFollowingCacheModel;
			}
		};

	private static CacheModel<Following> _nullFollowingCacheModel = new CacheModel<Following>() {
			public Following toEntityModel() {
				return _nullFollowing;
			}
		};
}