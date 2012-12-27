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

import net.sareweb.barazkide.NoSuchMembershipException;
import net.sareweb.barazkide.model.Membership;
import net.sareweb.barazkide.model.impl.MembershipImpl;
import net.sareweb.barazkide.model.impl.MembershipModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the membership service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author A.Galdos
 * @see MembershipPersistence
 * @see MembershipUtil
 * @generated
 */
public class MembershipPersistenceImpl extends BasePersistenceImpl<Membership>
	implements MembershipPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link MembershipUtil} to access the membership persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = MembershipImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GARDEN = new FinderPath(MembershipModelImpl.ENTITY_CACHE_ENABLED,
			MembershipModelImpl.FINDER_CACHE_ENABLED, MembershipImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGarden",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GARDEN =
		new FinderPath(MembershipModelImpl.ENTITY_CACHE_ENABLED,
			MembershipModelImpl.FINDER_CACHE_ENABLED, MembershipImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGarden",
			new String[] { Long.class.getName() },
			MembershipModelImpl.GARDENID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GARDEN = new FinderPath(MembershipModelImpl.ENTITY_CACHE_ENABLED,
			MembershipModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGarden",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GARDENANDSTATUS =
		new FinderPath(MembershipModelImpl.ENTITY_CACHE_ENABLED,
			MembershipModelImpl.FINDER_CACHE_ENABLED, MembershipImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGardenAndStatus",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GARDENANDSTATUS =
		new FinderPath(MembershipModelImpl.ENTITY_CACHE_ENABLED,
			MembershipModelImpl.FINDER_CACHE_ENABLED, MembershipImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGardenAndStatus",
			new String[] { Long.class.getName(), Integer.class.getName() },
			MembershipModelImpl.GARDENID_COLUMN_BITMASK |
			MembershipModelImpl.STATUS_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GARDENANDSTATUS = new FinderPath(MembershipModelImpl.ENTITY_CACHE_ENABLED,
			MembershipModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByGardenAndStatus",
			new String[] { Long.class.getName(), Integer.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_USERANDGARDEN = new FinderPath(MembershipModelImpl.ENTITY_CACHE_ENABLED,
			MembershipModelImpl.FINDER_CACHE_ENABLED, MembershipImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUserAndGarden",
			new String[] { Long.class.getName(), Long.class.getName() },
			MembershipModelImpl.USERID_COLUMN_BITMASK |
			MembershipModelImpl.GARDENID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_USERANDGARDEN = new FinderPath(MembershipModelImpl.ENTITY_CACHE_ENABLED,
			MembershipModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserAndGarden",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(MembershipModelImpl.ENTITY_CACHE_ENABLED,
			MembershipModelImpl.FINDER_CACHE_ENABLED, MembershipImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(MembershipModelImpl.ENTITY_CACHE_ENABLED,
			MembershipModelImpl.FINDER_CACHE_ENABLED, MembershipImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(MembershipModelImpl.ENTITY_CACHE_ENABLED,
			MembershipModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the membership in the entity cache if it is enabled.
	 *
	 * @param membership the membership
	 */
	public void cacheResult(Membership membership) {
		EntityCacheUtil.putResult(MembershipModelImpl.ENTITY_CACHE_ENABLED,
			MembershipImpl.class, membership.getPrimaryKey(), membership);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_USERANDGARDEN,
			new Object[] {
				Long.valueOf(membership.getUserId()),
				Long.valueOf(membership.getGardenId())
			}, membership);

		membership.resetOriginalValues();
	}

	/**
	 * Caches the memberships in the entity cache if it is enabled.
	 *
	 * @param memberships the memberships
	 */
	public void cacheResult(List<Membership> memberships) {
		for (Membership membership : memberships) {
			if (EntityCacheUtil.getResult(
						MembershipModelImpl.ENTITY_CACHE_ENABLED,
						MembershipImpl.class, membership.getPrimaryKey()) == null) {
				cacheResult(membership);
			}
			else {
				membership.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all memberships.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(MembershipImpl.class.getName());
		}

		EntityCacheUtil.clearCache(MembershipImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the membership.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Membership membership) {
		EntityCacheUtil.removeResult(MembershipModelImpl.ENTITY_CACHE_ENABLED,
			MembershipImpl.class, membership.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(membership);
	}

	@Override
	public void clearCache(List<Membership> memberships) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Membership membership : memberships) {
			EntityCacheUtil.removeResult(MembershipModelImpl.ENTITY_CACHE_ENABLED,
				MembershipImpl.class, membership.getPrimaryKey());

			clearUniqueFindersCache(membership);
		}
	}

	protected void clearUniqueFindersCache(Membership membership) {
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_USERANDGARDEN,
			new Object[] {
				Long.valueOf(membership.getUserId()),
				Long.valueOf(membership.getGardenId())
			});
	}

	/**
	 * Creates a new membership with the primary key. Does not add the membership to the database.
	 *
	 * @param membershipId the primary key for the new membership
	 * @return the new membership
	 */
	public Membership create(long membershipId) {
		Membership membership = new MembershipImpl();

		membership.setNew(true);
		membership.setPrimaryKey(membershipId);

		return membership;
	}

	/**
	 * Removes the membership with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param membershipId the primary key of the membership
	 * @return the membership that was removed
	 * @throws net.sareweb.barazkide.NoSuchMembershipException if a membership with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Membership remove(long membershipId)
		throws NoSuchMembershipException, SystemException {
		return remove(Long.valueOf(membershipId));
	}

	/**
	 * Removes the membership with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the membership
	 * @return the membership that was removed
	 * @throws net.sareweb.barazkide.NoSuchMembershipException if a membership with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Membership remove(Serializable primaryKey)
		throws NoSuchMembershipException, SystemException {
		Session session = null;

		try {
			session = openSession();

			Membership membership = (Membership)session.get(MembershipImpl.class,
					primaryKey);

			if (membership == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchMembershipException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(membership);
		}
		catch (NoSuchMembershipException nsee) {
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
	protected Membership removeImpl(Membership membership)
		throws SystemException {
		membership = toUnwrappedModel(membership);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, membership);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(membership);

		return membership;
	}

	@Override
	public Membership updateImpl(
		net.sareweb.barazkide.model.Membership membership, boolean merge)
		throws SystemException {
		membership = toUnwrappedModel(membership);

		boolean isNew = membership.isNew();

		MembershipModelImpl membershipModelImpl = (MembershipModelImpl)membership;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, membership, merge);

			membership.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !MembershipModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((membershipModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GARDEN.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(membershipModelImpl.getOriginalGardenId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GARDEN, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GARDEN,
					args);

				args = new Object[] {
						Long.valueOf(membershipModelImpl.getGardenId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GARDEN, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GARDEN,
					args);
			}

			if ((membershipModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GARDENANDSTATUS.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(membershipModelImpl.getOriginalGardenId()),
						Integer.valueOf(membershipModelImpl.getOriginalStatus())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GARDENANDSTATUS,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GARDENANDSTATUS,
					args);

				args = new Object[] {
						Long.valueOf(membershipModelImpl.getGardenId()),
						Integer.valueOf(membershipModelImpl.getStatus())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GARDENANDSTATUS,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GARDENANDSTATUS,
					args);
			}
		}

		EntityCacheUtil.putResult(MembershipModelImpl.ENTITY_CACHE_ENABLED,
			MembershipImpl.class, membership.getPrimaryKey(), membership);

		if (isNew) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_USERANDGARDEN,
				new Object[] {
					Long.valueOf(membership.getUserId()),
					Long.valueOf(membership.getGardenId())
				}, membership);
		}
		else {
			if ((membershipModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_USERANDGARDEN.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(membershipModelImpl.getOriginalUserId()),
						Long.valueOf(membershipModelImpl.getOriginalGardenId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERANDGARDEN,
					args);

				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_USERANDGARDEN,
					args);

				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_USERANDGARDEN,
					new Object[] {
						Long.valueOf(membership.getUserId()),
						Long.valueOf(membership.getGardenId())
					}, membership);
			}
		}

		return membership;
	}

	protected Membership toUnwrappedModel(Membership membership) {
		if (membership instanceof MembershipImpl) {
			return membership;
		}

		MembershipImpl membershipImpl = new MembershipImpl();

		membershipImpl.setNew(membership.isNew());
		membershipImpl.setPrimaryKey(membership.getPrimaryKey());

		membershipImpl.setMembershipId(membership.getMembershipId());
		membershipImpl.setUserId(membership.getUserId());
		membershipImpl.setGardenId(membership.getGardenId());
		membershipImpl.setMembershipDate(membership.getMembershipDate());
		membershipImpl.setStatus(membership.getStatus());

		return membershipImpl;
	}

	/**
	 * Returns the membership with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the membership
	 * @return the membership
	 * @throws com.liferay.portal.NoSuchModelException if a membership with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Membership findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the membership with the primary key or throws a {@link net.sareweb.barazkide.NoSuchMembershipException} if it could not be found.
	 *
	 * @param membershipId the primary key of the membership
	 * @return the membership
	 * @throws net.sareweb.barazkide.NoSuchMembershipException if a membership with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Membership findByPrimaryKey(long membershipId)
		throws NoSuchMembershipException, SystemException {
		Membership membership = fetchByPrimaryKey(membershipId);

		if (membership == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + membershipId);
			}

			throw new NoSuchMembershipException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				membershipId);
		}

		return membership;
	}

	/**
	 * Returns the membership with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the membership
	 * @return the membership, or <code>null</code> if a membership with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Membership fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the membership with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param membershipId the primary key of the membership
	 * @return the membership, or <code>null</code> if a membership with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Membership fetchByPrimaryKey(long membershipId)
		throws SystemException {
		Membership membership = (Membership)EntityCacheUtil.getResult(MembershipModelImpl.ENTITY_CACHE_ENABLED,
				MembershipImpl.class, membershipId);

		if (membership == _nullMembership) {
			return null;
		}

		if (membership == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				membership = (Membership)session.get(MembershipImpl.class,
						Long.valueOf(membershipId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (membership != null) {
					cacheResult(membership);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(MembershipModelImpl.ENTITY_CACHE_ENABLED,
						MembershipImpl.class, membershipId, _nullMembership);
				}

				closeSession(session);
			}
		}

		return membership;
	}

	/**
	 * Returns all the memberships where gardenId = &#63;.
	 *
	 * @param gardenId the garden ID
	 * @return the matching memberships
	 * @throws SystemException if a system exception occurred
	 */
	public List<Membership> findByGarden(long gardenId)
		throws SystemException {
		return findByGarden(gardenId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the memberships where gardenId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param gardenId the garden ID
	 * @param start the lower bound of the range of memberships
	 * @param end the upper bound of the range of memberships (not inclusive)
	 * @return the range of matching memberships
	 * @throws SystemException if a system exception occurred
	 */
	public List<Membership> findByGarden(long gardenId, int start, int end)
		throws SystemException {
		return findByGarden(gardenId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the memberships where gardenId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param gardenId the garden ID
	 * @param start the lower bound of the range of memberships
	 * @param end the upper bound of the range of memberships (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching memberships
	 * @throws SystemException if a system exception occurred
	 */
	public List<Membership> findByGarden(long gardenId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GARDEN;
			finderArgs = new Object[] { gardenId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GARDEN;
			finderArgs = new Object[] { gardenId, start, end, orderByComparator };
		}

		List<Membership> list = (List<Membership>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Membership membership : list) {
				if ((gardenId != membership.getGardenId())) {
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

			query.append(_SQL_SELECT_MEMBERSHIP_WHERE);

			query.append(_FINDER_COLUMN_GARDEN_GARDENID_2);

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

				qPos.add(gardenId);

				list = (List<Membership>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns the first membership in the ordered set where gardenId = &#63;.
	 *
	 * @param gardenId the garden ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching membership
	 * @throws net.sareweb.barazkide.NoSuchMembershipException if a matching membership could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Membership findByGarden_First(long gardenId,
		OrderByComparator orderByComparator)
		throws NoSuchMembershipException, SystemException {
		Membership membership = fetchByGarden_First(gardenId, orderByComparator);

		if (membership != null) {
			return membership;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("gardenId=");
		msg.append(gardenId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchMembershipException(msg.toString());
	}

	/**
	 * Returns the first membership in the ordered set where gardenId = &#63;.
	 *
	 * @param gardenId the garden ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching membership, or <code>null</code> if a matching membership could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Membership fetchByGarden_First(long gardenId,
		OrderByComparator orderByComparator) throws SystemException {
		List<Membership> list = findByGarden(gardenId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last membership in the ordered set where gardenId = &#63;.
	 *
	 * @param gardenId the garden ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching membership
	 * @throws net.sareweb.barazkide.NoSuchMembershipException if a matching membership could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Membership findByGarden_Last(long gardenId,
		OrderByComparator orderByComparator)
		throws NoSuchMembershipException, SystemException {
		Membership membership = fetchByGarden_Last(gardenId, orderByComparator);

		if (membership != null) {
			return membership;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("gardenId=");
		msg.append(gardenId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchMembershipException(msg.toString());
	}

	/**
	 * Returns the last membership in the ordered set where gardenId = &#63;.
	 *
	 * @param gardenId the garden ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching membership, or <code>null</code> if a matching membership could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Membership fetchByGarden_Last(long gardenId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByGarden(gardenId);

		List<Membership> list = findByGarden(gardenId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the memberships before and after the current membership in the ordered set where gardenId = &#63;.
	 *
	 * @param membershipId the primary key of the current membership
	 * @param gardenId the garden ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next membership
	 * @throws net.sareweb.barazkide.NoSuchMembershipException if a membership with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Membership[] findByGarden_PrevAndNext(long membershipId,
		long gardenId, OrderByComparator orderByComparator)
		throws NoSuchMembershipException, SystemException {
		Membership membership = findByPrimaryKey(membershipId);

		Session session = null;

		try {
			session = openSession();

			Membership[] array = new MembershipImpl[3];

			array[0] = getByGarden_PrevAndNext(session, membership, gardenId,
					orderByComparator, true);

			array[1] = membership;

			array[2] = getByGarden_PrevAndNext(session, membership, gardenId,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Membership getByGarden_PrevAndNext(Session session,
		Membership membership, long gardenId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_MEMBERSHIP_WHERE);

		query.append(_FINDER_COLUMN_GARDEN_GARDENID_2);

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

		qPos.add(gardenId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(membership);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Membership> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the memberships where gardenId = &#63; and status = &#63;.
	 *
	 * @param gardenId the garden ID
	 * @param status the status
	 * @return the matching memberships
	 * @throws SystemException if a system exception occurred
	 */
	public List<Membership> findByGardenAndStatus(long gardenId, int status)
		throws SystemException {
		return findByGardenAndStatus(gardenId, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the memberships where gardenId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param gardenId the garden ID
	 * @param status the status
	 * @param start the lower bound of the range of memberships
	 * @param end the upper bound of the range of memberships (not inclusive)
	 * @return the range of matching memberships
	 * @throws SystemException if a system exception occurred
	 */
	public List<Membership> findByGardenAndStatus(long gardenId, int status,
		int start, int end) throws SystemException {
		return findByGardenAndStatus(gardenId, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the memberships where gardenId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param gardenId the garden ID
	 * @param status the status
	 * @param start the lower bound of the range of memberships
	 * @param end the upper bound of the range of memberships (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching memberships
	 * @throws SystemException if a system exception occurred
	 */
	public List<Membership> findByGardenAndStatus(long gardenId, int status,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GARDENANDSTATUS;
			finderArgs = new Object[] { gardenId, status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GARDENANDSTATUS;
			finderArgs = new Object[] {
					gardenId, status,
					
					start, end, orderByComparator
				};
		}

		List<Membership> list = (List<Membership>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Membership membership : list) {
				if ((gardenId != membership.getGardenId()) ||
						(status != membership.getStatus())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_MEMBERSHIP_WHERE);

			query.append(_FINDER_COLUMN_GARDENANDSTATUS_GARDENID_2);

			query.append(_FINDER_COLUMN_GARDENANDSTATUS_STATUS_2);

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

				qPos.add(gardenId);

				qPos.add(status);

				list = (List<Membership>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns the first membership in the ordered set where gardenId = &#63; and status = &#63;.
	 *
	 * @param gardenId the garden ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching membership
	 * @throws net.sareweb.barazkide.NoSuchMembershipException if a matching membership could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Membership findByGardenAndStatus_First(long gardenId, int status,
		OrderByComparator orderByComparator)
		throws NoSuchMembershipException, SystemException {
		Membership membership = fetchByGardenAndStatus_First(gardenId, status,
				orderByComparator);

		if (membership != null) {
			return membership;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("gardenId=");
		msg.append(gardenId);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchMembershipException(msg.toString());
	}

	/**
	 * Returns the first membership in the ordered set where gardenId = &#63; and status = &#63;.
	 *
	 * @param gardenId the garden ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching membership, or <code>null</code> if a matching membership could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Membership fetchByGardenAndStatus_First(long gardenId, int status,
		OrderByComparator orderByComparator) throws SystemException {
		List<Membership> list = findByGardenAndStatus(gardenId, status, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last membership in the ordered set where gardenId = &#63; and status = &#63;.
	 *
	 * @param gardenId the garden ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching membership
	 * @throws net.sareweb.barazkide.NoSuchMembershipException if a matching membership could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Membership findByGardenAndStatus_Last(long gardenId, int status,
		OrderByComparator orderByComparator)
		throws NoSuchMembershipException, SystemException {
		Membership membership = fetchByGardenAndStatus_Last(gardenId, status,
				orderByComparator);

		if (membership != null) {
			return membership;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("gardenId=");
		msg.append(gardenId);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchMembershipException(msg.toString());
	}

	/**
	 * Returns the last membership in the ordered set where gardenId = &#63; and status = &#63;.
	 *
	 * @param gardenId the garden ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching membership, or <code>null</code> if a matching membership could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Membership fetchByGardenAndStatus_Last(long gardenId, int status,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByGardenAndStatus(gardenId, status);

		List<Membership> list = findByGardenAndStatus(gardenId, status,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the memberships before and after the current membership in the ordered set where gardenId = &#63; and status = &#63;.
	 *
	 * @param membershipId the primary key of the current membership
	 * @param gardenId the garden ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next membership
	 * @throws net.sareweb.barazkide.NoSuchMembershipException if a membership with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Membership[] findByGardenAndStatus_PrevAndNext(long membershipId,
		long gardenId, int status, OrderByComparator orderByComparator)
		throws NoSuchMembershipException, SystemException {
		Membership membership = findByPrimaryKey(membershipId);

		Session session = null;

		try {
			session = openSession();

			Membership[] array = new MembershipImpl[3];

			array[0] = getByGardenAndStatus_PrevAndNext(session, membership,
					gardenId, status, orderByComparator, true);

			array[1] = membership;

			array[2] = getByGardenAndStatus_PrevAndNext(session, membership,
					gardenId, status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Membership getByGardenAndStatus_PrevAndNext(Session session,
		Membership membership, long gardenId, int status,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_MEMBERSHIP_WHERE);

		query.append(_FINDER_COLUMN_GARDENANDSTATUS_GARDENID_2);

		query.append(_FINDER_COLUMN_GARDENANDSTATUS_STATUS_2);

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

		qPos.add(gardenId);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(membership);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Membership> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the membership where userId = &#63; and gardenId = &#63; or throws a {@link net.sareweb.barazkide.NoSuchMembershipException} if it could not be found.
	 *
	 * @param userId the user ID
	 * @param gardenId the garden ID
	 * @return the matching membership
	 * @throws net.sareweb.barazkide.NoSuchMembershipException if a matching membership could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Membership findByUserAndGarden(long userId, long gardenId)
		throws NoSuchMembershipException, SystemException {
		Membership membership = fetchByUserAndGarden(userId, gardenId);

		if (membership == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("userId=");
			msg.append(userId);

			msg.append(", gardenId=");
			msg.append(gardenId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchMembershipException(msg.toString());
		}

		return membership;
	}

	/**
	 * Returns the membership where userId = &#63; and gardenId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param userId the user ID
	 * @param gardenId the garden ID
	 * @return the matching membership, or <code>null</code> if a matching membership could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Membership fetchByUserAndGarden(long userId, long gardenId)
		throws SystemException {
		return fetchByUserAndGarden(userId, gardenId, true);
	}

	/**
	 * Returns the membership where userId = &#63; and gardenId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param userId the user ID
	 * @param gardenId the garden ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching membership, or <code>null</code> if a matching membership could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Membership fetchByUserAndGarden(long userId, long gardenId,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { userId, gardenId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_USERANDGARDEN,
					finderArgs, this);
		}

		if (result instanceof Membership) {
			Membership membership = (Membership)result;

			if ((userId != membership.getUserId()) ||
					(gardenId != membership.getGardenId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_MEMBERSHIP_WHERE);

			query.append(_FINDER_COLUMN_USERANDGARDEN_USERID_2);

			query.append(_FINDER_COLUMN_USERANDGARDEN_GARDENID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(gardenId);

				List<Membership> list = q.list();

				result = list;

				Membership membership = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_USERANDGARDEN,
						finderArgs, list);
				}
				else {
					membership = list.get(0);

					cacheResult(membership);

					if ((membership.getUserId() != userId) ||
							(membership.getGardenId() != gardenId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_USERANDGARDEN,
							finderArgs, membership);
					}
				}

				return membership;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_USERANDGARDEN,
						finderArgs);
				}

				closeSession(session);
			}
		}
		else {
			if (result instanceof List<?>) {
				return null;
			}
			else {
				return (Membership)result;
			}
		}
	}

	/**
	 * Returns all the memberships.
	 *
	 * @return the memberships
	 * @throws SystemException if a system exception occurred
	 */
	public List<Membership> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the memberships.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of memberships
	 * @param end the upper bound of the range of memberships (not inclusive)
	 * @return the range of memberships
	 * @throws SystemException if a system exception occurred
	 */
	public List<Membership> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the memberships.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of memberships
	 * @param end the upper bound of the range of memberships (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of memberships
	 * @throws SystemException if a system exception occurred
	 */
	public List<Membership> findAll(int start, int end,
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

		List<Membership> list = (List<Membership>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_MEMBERSHIP);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_MEMBERSHIP;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<Membership>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<Membership>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the memberships where gardenId = &#63; from the database.
	 *
	 * @param gardenId the garden ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByGarden(long gardenId) throws SystemException {
		for (Membership membership : findByGarden(gardenId)) {
			remove(membership);
		}
	}

	/**
	 * Removes all the memberships where gardenId = &#63; and status = &#63; from the database.
	 *
	 * @param gardenId the garden ID
	 * @param status the status
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByGardenAndStatus(long gardenId, int status)
		throws SystemException {
		for (Membership membership : findByGardenAndStatus(gardenId, status)) {
			remove(membership);
		}
	}

	/**
	 * Removes the membership where userId = &#63; and gardenId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param gardenId the garden ID
	 * @return the membership that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public Membership removeByUserAndGarden(long userId, long gardenId)
		throws NoSuchMembershipException, SystemException {
		Membership membership = findByUserAndGarden(userId, gardenId);

		return remove(membership);
	}

	/**
	 * Removes all the memberships from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (Membership membership : findAll()) {
			remove(membership);
		}
	}

	/**
	 * Returns the number of memberships where gardenId = &#63;.
	 *
	 * @param gardenId the garden ID
	 * @return the number of matching memberships
	 * @throws SystemException if a system exception occurred
	 */
	public int countByGarden(long gardenId) throws SystemException {
		Object[] finderArgs = new Object[] { gardenId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_GARDEN,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_MEMBERSHIP_WHERE);

			query.append(_FINDER_COLUMN_GARDEN_GARDENID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(gardenId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_GARDEN,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of memberships where gardenId = &#63; and status = &#63;.
	 *
	 * @param gardenId the garden ID
	 * @param status the status
	 * @return the number of matching memberships
	 * @throws SystemException if a system exception occurred
	 */
	public int countByGardenAndStatus(long gardenId, int status)
		throws SystemException {
		Object[] finderArgs = new Object[] { gardenId, status };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_GARDENANDSTATUS,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_MEMBERSHIP_WHERE);

			query.append(_FINDER_COLUMN_GARDENANDSTATUS_GARDENID_2);

			query.append(_FINDER_COLUMN_GARDENANDSTATUS_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(gardenId);

				qPos.add(status);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_GARDENANDSTATUS,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of memberships where userId = &#63; and gardenId = &#63;.
	 *
	 * @param userId the user ID
	 * @param gardenId the garden ID
	 * @return the number of matching memberships
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUserAndGarden(long userId, long gardenId)
		throws SystemException {
		Object[] finderArgs = new Object[] { userId, gardenId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_USERANDGARDEN,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_MEMBERSHIP_WHERE);

			query.append(_FINDER_COLUMN_USERANDGARDEN_USERID_2);

			query.append(_FINDER_COLUMN_USERANDGARDEN_GARDENID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(gardenId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_USERANDGARDEN,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of memberships.
	 *
	 * @return the number of memberships
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_MEMBERSHIP);

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
	 * Initializes the membership persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.net.sareweb.barazkide.model.Membership")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<Membership>> listenersList = new ArrayList<ModelListener<Membership>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<Membership>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(MembershipImpl.class.getName());
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
	private static final String _SQL_SELECT_MEMBERSHIP = "SELECT membership FROM Membership membership";
	private static final String _SQL_SELECT_MEMBERSHIP_WHERE = "SELECT membership FROM Membership membership WHERE ";
	private static final String _SQL_COUNT_MEMBERSHIP = "SELECT COUNT(membership) FROM Membership membership";
	private static final String _SQL_COUNT_MEMBERSHIP_WHERE = "SELECT COUNT(membership) FROM Membership membership WHERE ";
	private static final String _FINDER_COLUMN_GARDEN_GARDENID_2 = "membership.gardenId = ?";
	private static final String _FINDER_COLUMN_GARDENANDSTATUS_GARDENID_2 = "membership.gardenId = ? AND ";
	private static final String _FINDER_COLUMN_GARDENANDSTATUS_STATUS_2 = "membership.status = ?";
	private static final String _FINDER_COLUMN_USERANDGARDEN_USERID_2 = "membership.userId = ? AND ";
	private static final String _FINDER_COLUMN_USERANDGARDEN_GARDENID_2 = "membership.gardenId = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "membership.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Membership exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Membership exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(MembershipPersistenceImpl.class);
	private static Membership _nullMembership = new MembershipImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<Membership> toCacheModel() {
				return _nullMembershipCacheModel;
			}
		};

	private static CacheModel<Membership> _nullMembershipCacheModel = new CacheModel<Membership>() {
			public Membership toEntityModel() {
				return _nullMembership;
			}
		};
}