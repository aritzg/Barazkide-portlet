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

import net.sareweb.barazkide.NoSuchGardenImageException;
import net.sareweb.barazkide.model.GardenImage;
import net.sareweb.barazkide.model.impl.GardenImageImpl;
import net.sareweb.barazkide.model.impl.GardenImageModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the garden image service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author A.Galdos
 * @see GardenImagePersistence
 * @see GardenImageUtil
 * @generated
 */
public class GardenImagePersistenceImpl extends BasePersistenceImpl<GardenImage>
	implements GardenImagePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link GardenImageUtil} to access the garden image persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = GardenImageImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(GardenImageModelImpl.ENTITY_CACHE_ENABLED,
			GardenImageModelImpl.FINDER_CACHE_ENABLED, GardenImageImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(GardenImageModelImpl.ENTITY_CACHE_ENABLED,
			GardenImageModelImpl.FINDER_CACHE_ENABLED, GardenImageImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(GardenImageModelImpl.ENTITY_CACHE_ENABLED,
			GardenImageModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the garden image in the entity cache if it is enabled.
	 *
	 * @param gardenImage the garden image
	 */
	public void cacheResult(GardenImage gardenImage) {
		EntityCacheUtil.putResult(GardenImageModelImpl.ENTITY_CACHE_ENABLED,
			GardenImageImpl.class, gardenImage.getPrimaryKey(), gardenImage);

		gardenImage.resetOriginalValues();
	}

	/**
	 * Caches the garden images in the entity cache if it is enabled.
	 *
	 * @param gardenImages the garden images
	 */
	public void cacheResult(List<GardenImage> gardenImages) {
		for (GardenImage gardenImage : gardenImages) {
			if (EntityCacheUtil.getResult(
						GardenImageModelImpl.ENTITY_CACHE_ENABLED,
						GardenImageImpl.class, gardenImage.getPrimaryKey()) == null) {
				cacheResult(gardenImage);
			}
			else {
				gardenImage.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all garden images.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(GardenImageImpl.class.getName());
		}

		EntityCacheUtil.clearCache(GardenImageImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the garden image.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(GardenImage gardenImage) {
		EntityCacheUtil.removeResult(GardenImageModelImpl.ENTITY_CACHE_ENABLED,
			GardenImageImpl.class, gardenImage.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<GardenImage> gardenImages) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (GardenImage gardenImage : gardenImages) {
			EntityCacheUtil.removeResult(GardenImageModelImpl.ENTITY_CACHE_ENABLED,
				GardenImageImpl.class, gardenImage.getPrimaryKey());
		}
	}

	/**
	 * Creates a new garden image with the primary key. Does not add the garden image to the database.
	 *
	 * @param gardenImageId the primary key for the new garden image
	 * @return the new garden image
	 */
	public GardenImage create(long gardenImageId) {
		GardenImage gardenImage = new GardenImageImpl();

		gardenImage.setNew(true);
		gardenImage.setPrimaryKey(gardenImageId);

		return gardenImage;
	}

	/**
	 * Removes the garden image with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param gardenImageId the primary key of the garden image
	 * @return the garden image that was removed
	 * @throws net.sareweb.barazkide.NoSuchGardenImageException if a garden image with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public GardenImage remove(long gardenImageId)
		throws NoSuchGardenImageException, SystemException {
		return remove(Long.valueOf(gardenImageId));
	}

	/**
	 * Removes the garden image with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the garden image
	 * @return the garden image that was removed
	 * @throws net.sareweb.barazkide.NoSuchGardenImageException if a garden image with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public GardenImage remove(Serializable primaryKey)
		throws NoSuchGardenImageException, SystemException {
		Session session = null;

		try {
			session = openSession();

			GardenImage gardenImage = (GardenImage)session.get(GardenImageImpl.class,
					primaryKey);

			if (gardenImage == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchGardenImageException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(gardenImage);
		}
		catch (NoSuchGardenImageException nsee) {
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
	protected GardenImage removeImpl(GardenImage gardenImage)
		throws SystemException {
		gardenImage = toUnwrappedModel(gardenImage);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, gardenImage);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(gardenImage);

		return gardenImage;
	}

	@Override
	public GardenImage updateImpl(
		net.sareweb.barazkide.model.GardenImage gardenImage, boolean merge)
		throws SystemException {
		gardenImage = toUnwrappedModel(gardenImage);

		boolean isNew = gardenImage.isNew();

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, gardenImage, merge);

			gardenImage.setNew(false);
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

		EntityCacheUtil.putResult(GardenImageModelImpl.ENTITY_CACHE_ENABLED,
			GardenImageImpl.class, gardenImage.getPrimaryKey(), gardenImage);

		return gardenImage;
	}

	protected GardenImage toUnwrappedModel(GardenImage gardenImage) {
		if (gardenImage instanceof GardenImageImpl) {
			return gardenImage;
		}

		GardenImageImpl gardenImageImpl = new GardenImageImpl();

		gardenImageImpl.setNew(gardenImage.isNew());
		gardenImageImpl.setPrimaryKey(gardenImage.getPrimaryKey());

		gardenImageImpl.setGardenImageId(gardenImage.getGardenImageId());
		gardenImageImpl.setGardenId(gardenImage.getGardenId());
		gardenImageImpl.setImgId(gardenImage.getImgId());
		gardenImageImpl.setImgName(gardenImage.getImgName());
		gardenImageImpl.setImgUrl(gardenImage.getImgUrl());
		gardenImageImpl.setCreateDate(gardenImage.getCreateDate());

		return gardenImageImpl;
	}

	/**
	 * Returns the garden image with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the garden image
	 * @return the garden image
	 * @throws com.liferay.portal.NoSuchModelException if a garden image with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public GardenImage findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the garden image with the primary key or throws a {@link net.sareweb.barazkide.NoSuchGardenImageException} if it could not be found.
	 *
	 * @param gardenImageId the primary key of the garden image
	 * @return the garden image
	 * @throws net.sareweb.barazkide.NoSuchGardenImageException if a garden image with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public GardenImage findByPrimaryKey(long gardenImageId)
		throws NoSuchGardenImageException, SystemException {
		GardenImage gardenImage = fetchByPrimaryKey(gardenImageId);

		if (gardenImage == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + gardenImageId);
			}

			throw new NoSuchGardenImageException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				gardenImageId);
		}

		return gardenImage;
	}

	/**
	 * Returns the garden image with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the garden image
	 * @return the garden image, or <code>null</code> if a garden image with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public GardenImage fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the garden image with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param gardenImageId the primary key of the garden image
	 * @return the garden image, or <code>null</code> if a garden image with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public GardenImage fetchByPrimaryKey(long gardenImageId)
		throws SystemException {
		GardenImage gardenImage = (GardenImage)EntityCacheUtil.getResult(GardenImageModelImpl.ENTITY_CACHE_ENABLED,
				GardenImageImpl.class, gardenImageId);

		if (gardenImage == _nullGardenImage) {
			return null;
		}

		if (gardenImage == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				gardenImage = (GardenImage)session.get(GardenImageImpl.class,
						Long.valueOf(gardenImageId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (gardenImage != null) {
					cacheResult(gardenImage);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(GardenImageModelImpl.ENTITY_CACHE_ENABLED,
						GardenImageImpl.class, gardenImageId, _nullGardenImage);
				}

				closeSession(session);
			}
		}

		return gardenImage;
	}

	/**
	 * Returns all the garden images.
	 *
	 * @return the garden images
	 * @throws SystemException if a system exception occurred
	 */
	public List<GardenImage> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the garden images.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of garden images
	 * @param end the upper bound of the range of garden images (not inclusive)
	 * @return the range of garden images
	 * @throws SystemException if a system exception occurred
	 */
	public List<GardenImage> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the garden images.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of garden images
	 * @param end the upper bound of the range of garden images (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of garden images
	 * @throws SystemException if a system exception occurred
	 */
	public List<GardenImage> findAll(int start, int end,
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

		List<GardenImage> list = (List<GardenImage>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_GARDENIMAGE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_GARDENIMAGE;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<GardenImage>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<GardenImage>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the garden images from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (GardenImage gardenImage : findAll()) {
			remove(gardenImage);
		}
	}

	/**
	 * Returns the number of garden images.
	 *
	 * @return the number of garden images
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_GARDENIMAGE);

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
	 * Initializes the garden image persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.net.sareweb.barazkide.model.GardenImage")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<GardenImage>> listenersList = new ArrayList<ModelListener<GardenImage>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<GardenImage>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(GardenImageImpl.class.getName());
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
	private static final String _SQL_SELECT_GARDENIMAGE = "SELECT gardenImage FROM GardenImage gardenImage";
	private static final String _SQL_COUNT_GARDENIMAGE = "SELECT COUNT(gardenImage) FROM GardenImage gardenImage";
	private static final String _ORDER_BY_ENTITY_ALIAS = "gardenImage.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No GardenImage exists with the primary key ";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(GardenImagePersistenceImpl.class);
	private static GardenImage _nullGardenImage = new GardenImageImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<GardenImage> toCacheModel() {
				return _nullGardenImageCacheModel;
			}
		};

	private static CacheModel<GardenImage> _nullGardenImageCacheModel = new CacheModel<GardenImage>() {
			public GardenImage toEntityModel() {
				return _nullGardenImage;
			}
		};
}