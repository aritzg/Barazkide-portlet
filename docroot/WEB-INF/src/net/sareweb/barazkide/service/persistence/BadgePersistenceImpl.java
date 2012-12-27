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

import net.sareweb.barazkide.NoSuchBadgeException;
import net.sareweb.barazkide.model.Badge;
import net.sareweb.barazkide.model.impl.BadgeImpl;
import net.sareweb.barazkide.model.impl.BadgeModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the badge service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author A.Galdos
 * @see BadgePersistence
 * @see BadgeUtil
 * @generated
 */
public class BadgePersistenceImpl extends BasePersistenceImpl<Badge>
	implements BadgePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link BadgeUtil} to access the badge persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = BadgeImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(BadgeModelImpl.ENTITY_CACHE_ENABLED,
			BadgeModelImpl.FINDER_CACHE_ENABLED, BadgeImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(BadgeModelImpl.ENTITY_CACHE_ENABLED,
			BadgeModelImpl.FINDER_CACHE_ENABLED, BadgeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(BadgeModelImpl.ENTITY_CACHE_ENABLED,
			BadgeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the badge in the entity cache if it is enabled.
	 *
	 * @param badge the badge
	 */
	public void cacheResult(Badge badge) {
		EntityCacheUtil.putResult(BadgeModelImpl.ENTITY_CACHE_ENABLED,
			BadgeImpl.class, badge.getPrimaryKey(), badge);

		badge.resetOriginalValues();
	}

	/**
	 * Caches the badges in the entity cache if it is enabled.
	 *
	 * @param badges the badges
	 */
	public void cacheResult(List<Badge> badges) {
		for (Badge badge : badges) {
			if (EntityCacheUtil.getResult(BadgeModelImpl.ENTITY_CACHE_ENABLED,
						BadgeImpl.class, badge.getPrimaryKey()) == null) {
				cacheResult(badge);
			}
			else {
				badge.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all badges.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(BadgeImpl.class.getName());
		}

		EntityCacheUtil.clearCache(BadgeImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the badge.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Badge badge) {
		EntityCacheUtil.removeResult(BadgeModelImpl.ENTITY_CACHE_ENABLED,
			BadgeImpl.class, badge.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<Badge> badges) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Badge badge : badges) {
			EntityCacheUtil.removeResult(BadgeModelImpl.ENTITY_CACHE_ENABLED,
				BadgeImpl.class, badge.getPrimaryKey());
		}
	}

	/**
	 * Creates a new badge with the primary key. Does not add the badge to the database.
	 *
	 * @param badgeId the primary key for the new badge
	 * @return the new badge
	 */
	public Badge create(long badgeId) {
		Badge badge = new BadgeImpl();

		badge.setNew(true);
		badge.setPrimaryKey(badgeId);

		return badge;
	}

	/**
	 * Removes the badge with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param badgeId the primary key of the badge
	 * @return the badge that was removed
	 * @throws net.sareweb.barazkide.NoSuchBadgeException if a badge with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Badge remove(long badgeId)
		throws NoSuchBadgeException, SystemException {
		return remove(Long.valueOf(badgeId));
	}

	/**
	 * Removes the badge with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the badge
	 * @return the badge that was removed
	 * @throws net.sareweb.barazkide.NoSuchBadgeException if a badge with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Badge remove(Serializable primaryKey)
		throws NoSuchBadgeException, SystemException {
		Session session = null;

		try {
			session = openSession();

			Badge badge = (Badge)session.get(BadgeImpl.class, primaryKey);

			if (badge == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchBadgeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(badge);
		}
		catch (NoSuchBadgeException nsee) {
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
	protected Badge removeImpl(Badge badge) throws SystemException {
		badge = toUnwrappedModel(badge);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, badge);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(badge);

		return badge;
	}

	@Override
	public Badge updateImpl(net.sareweb.barazkide.model.Badge badge,
		boolean merge) throws SystemException {
		badge = toUnwrappedModel(badge);

		boolean isNew = badge.isNew();

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, badge, merge);

			badge.setNew(false);
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

		EntityCacheUtil.putResult(BadgeModelImpl.ENTITY_CACHE_ENABLED,
			BadgeImpl.class, badge.getPrimaryKey(), badge);

		return badge;
	}

	protected Badge toUnwrappedModel(Badge badge) {
		if (badge instanceof BadgeImpl) {
			return badge;
		}

		BadgeImpl badgeImpl = new BadgeImpl();

		badgeImpl.setNew(badge.isNew());
		badgeImpl.setPrimaryKey(badge.getPrimaryKey());

		badgeImpl.setBadgeId(badge.getBadgeId());
		badgeImpl.setBadgeType(badge.getBadgeType());
		badgeImpl.setBadgeTextKey(badge.getBadgeTextKey());
		badgeImpl.setBadgeImageUrl(badge.getBadgeImageUrl());
		badgeImpl.setCreateDate(badge.getCreateDate());

		return badgeImpl;
	}

	/**
	 * Returns the badge with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the badge
	 * @return the badge
	 * @throws com.liferay.portal.NoSuchModelException if a badge with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Badge findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the badge with the primary key or throws a {@link net.sareweb.barazkide.NoSuchBadgeException} if it could not be found.
	 *
	 * @param badgeId the primary key of the badge
	 * @return the badge
	 * @throws net.sareweb.barazkide.NoSuchBadgeException if a badge with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Badge findByPrimaryKey(long badgeId)
		throws NoSuchBadgeException, SystemException {
		Badge badge = fetchByPrimaryKey(badgeId);

		if (badge == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + badgeId);
			}

			throw new NoSuchBadgeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				badgeId);
		}

		return badge;
	}

	/**
	 * Returns the badge with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the badge
	 * @return the badge, or <code>null</code> if a badge with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Badge fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the badge with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param badgeId the primary key of the badge
	 * @return the badge, or <code>null</code> if a badge with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Badge fetchByPrimaryKey(long badgeId) throws SystemException {
		Badge badge = (Badge)EntityCacheUtil.getResult(BadgeModelImpl.ENTITY_CACHE_ENABLED,
				BadgeImpl.class, badgeId);

		if (badge == _nullBadge) {
			return null;
		}

		if (badge == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				badge = (Badge)session.get(BadgeImpl.class,
						Long.valueOf(badgeId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (badge != null) {
					cacheResult(badge);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(BadgeModelImpl.ENTITY_CACHE_ENABLED,
						BadgeImpl.class, badgeId, _nullBadge);
				}

				closeSession(session);
			}
		}

		return badge;
	}

	/**
	 * Returns all the badges.
	 *
	 * @return the badges
	 * @throws SystemException if a system exception occurred
	 */
	public List<Badge> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the badges.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of badges
	 * @param end the upper bound of the range of badges (not inclusive)
	 * @return the range of badges
	 * @throws SystemException if a system exception occurred
	 */
	public List<Badge> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the badges.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of badges
	 * @param end the upper bound of the range of badges (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of badges
	 * @throws SystemException if a system exception occurred
	 */
	public List<Badge> findAll(int start, int end,
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

		List<Badge> list = (List<Badge>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_BADGE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_BADGE;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<Badge>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);
				}
				else {
					list = (List<Badge>)QueryUtil.list(q, getDialect(), start,
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
	 * Removes all the badges from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (Badge badge : findAll()) {
			remove(badge);
		}
	}

	/**
	 * Returns the number of badges.
	 *
	 * @return the number of badges
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_BADGE);

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
	 * Initializes the badge persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.net.sareweb.barazkide.model.Badge")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<Badge>> listenersList = new ArrayList<ModelListener<Badge>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<Badge>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(BadgeImpl.class.getName());
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
	private static final String _SQL_SELECT_BADGE = "SELECT badge FROM Badge badge";
	private static final String _SQL_COUNT_BADGE = "SELECT COUNT(badge) FROM Badge badge";
	private static final String _ORDER_BY_ENTITY_ALIAS = "badge.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Badge exists with the primary key ";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(BadgePersistenceImpl.class);
	private static Badge _nullBadge = new BadgeImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<Badge> toCacheModel() {
				return _nullBadgeCacheModel;
			}
		};

	private static CacheModel<Badge> _nullBadgeCacheModel = new CacheModel<Badge>() {
			public Badge toEntityModel() {
				return _nullBadge;
			}
		};
}