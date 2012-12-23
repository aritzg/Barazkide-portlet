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

package net.sareweb.barazkide.model.impl;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortalUtil;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import net.sareweb.barazkide.model.Garden;
import net.sareweb.barazkide.model.GardenModel;
import net.sareweb.barazkide.model.GardenSoap;

import java.io.Serializable;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The base model implementation for the Garden service. Represents a row in the &quot;Barazkide_Garden&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link net.sareweb.barazkide.model.GardenModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link GardenImpl}.
 * </p>
 *
 * @author A.Galdos
 * @see GardenImpl
 * @see net.sareweb.barazkide.model.Garden
 * @see net.sareweb.barazkide.model.GardenModel
 * @generated
 */
@JSON(strict = true)
public class GardenModelImpl extends BaseModelImpl<Garden>
	implements GardenModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a garden model instance should use the {@link net.sareweb.barazkide.model.Garden} interface instead.
	 */
	public static final String TABLE_NAME = "Barazkide_Garden";
	public static final Object[][] TABLE_COLUMNS = {
			{ "gardenId", Types.BIGINT },
			{ "gardenFolderId", Types.BIGINT },
			{ "gardenImageId", Types.BIGINT },
			{ "ownerUserId", Types.BIGINT },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "name", Types.VARCHAR },
			{ "comment_", Types.VARCHAR },
			{ "lat", Types.DOUBLE },
			{ "lng", Types.DOUBLE },
			{ "imageTitle", Types.VARCHAR }
		};
	public static final String TABLE_SQL_CREATE = "create table Barazkide_Garden (gardenId LONG not null primary key,gardenFolderId LONG,gardenImageId LONG,ownerUserId LONG,createDate DATE null,modifiedDate DATE null,name VARCHAR(75) null,comment_ VARCHAR(75) null,lat DOUBLE,lng DOUBLE,imageTitle VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table Barazkide_Garden";
	public static final String ORDER_BY_JPQL = " ORDER BY garden.createDate DESC";
	public static final String ORDER_BY_SQL = " ORDER BY Barazkide_Garden.createDate DESC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.entity.cache.enabled.net.sareweb.barazkide.model.Garden"),
			false);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.net.sareweb.barazkide.model.Garden"),
			false);
	public static final boolean COLUMN_BITMASK_ENABLED = false;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static Garden toModel(GardenSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		Garden model = new GardenImpl();

		model.setGardenId(soapModel.getGardenId());
		model.setGardenFolderId(soapModel.getGardenFolderId());
		model.setGardenImageId(soapModel.getGardenImageId());
		model.setOwnerUserId(soapModel.getOwnerUserId());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setName(soapModel.getName());
		model.setComment(soapModel.getComment());
		model.setLat(soapModel.getLat());
		model.setLng(soapModel.getLng());
		model.setImageTitle(soapModel.getImageTitle());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<Garden> toModels(GardenSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<Garden> models = new ArrayList<Garden>(soapModels.length);

		for (GardenSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.net.sareweb.barazkide.model.Garden"));

	public GardenModelImpl() {
	}

	public long getPrimaryKey() {
		return _gardenId;
	}

	public void setPrimaryKey(long primaryKey) {
		setGardenId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_gardenId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	public Class<?> getModelClass() {
		return Garden.class;
	}

	public String getModelClassName() {
		return Garden.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("gardenId", getGardenId());
		attributes.put("gardenFolderId", getGardenFolderId());
		attributes.put("gardenImageId", getGardenImageId());
		attributes.put("ownerUserId", getOwnerUserId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("name", getName());
		attributes.put("comment", getComment());
		attributes.put("lat", getLat());
		attributes.put("lng", getLng());
		attributes.put("imageTitle", getImageTitle());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long gardenId = (Long)attributes.get("gardenId");

		if (gardenId != null) {
			setGardenId(gardenId);
		}

		Long gardenFolderId = (Long)attributes.get("gardenFolderId");

		if (gardenFolderId != null) {
			setGardenFolderId(gardenFolderId);
		}

		Long gardenImageId = (Long)attributes.get("gardenImageId");

		if (gardenImageId != null) {
			setGardenImageId(gardenImageId);
		}

		Long ownerUserId = (Long)attributes.get("ownerUserId");

		if (ownerUserId != null) {
			setOwnerUserId(ownerUserId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String comment = (String)attributes.get("comment");

		if (comment != null) {
			setComment(comment);
		}

		Double lat = (Double)attributes.get("lat");

		if (lat != null) {
			setLat(lat);
		}

		Double lng = (Double)attributes.get("lng");

		if (lng != null) {
			setLng(lng);
		}

		String imageTitle = (String)attributes.get("imageTitle");

		if (imageTitle != null) {
			setImageTitle(imageTitle);
		}
	}

	@JSON
	public long getGardenId() {
		return _gardenId;
	}

	public void setGardenId(long gardenId) {
		_gardenId = gardenId;
	}

	@JSON
	public long getGardenFolderId() {
		return _gardenFolderId;
	}

	public void setGardenFolderId(long gardenFolderId) {
		_gardenFolderId = gardenFolderId;
	}

	@JSON
	public long getGardenImageId() {
		return _gardenImageId;
	}

	public void setGardenImageId(long gardenImageId) {
		_gardenImageId = gardenImageId;
	}

	@JSON
	public long getOwnerUserId() {
		return _ownerUserId;
	}

	public void setOwnerUserId(long ownerUserId) {
		_ownerUserId = ownerUserId;
	}

	public String getOwnerUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getOwnerUserId(), "uuid", _ownerUserUuid);
	}

	public void setOwnerUserUuid(String ownerUserUuid) {
		_ownerUserUuid = ownerUserUuid;
	}

	@JSON
	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	@JSON
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	@JSON
	public String getName() {
		if (_name == null) {
			return StringPool.BLANK;
		}
		else {
			return _name;
		}
	}

	public void setName(String name) {
		_name = name;
	}

	@JSON
	public String getComment() {
		if (_comment == null) {
			return StringPool.BLANK;
		}
		else {
			return _comment;
		}
	}

	public void setComment(String comment) {
		_comment = comment;
	}

	@JSON
	public double getLat() {
		return _lat;
	}

	public void setLat(double lat) {
		_lat = lat;
	}

	@JSON
	public double getLng() {
		return _lng;
	}

	public void setLng(double lng) {
		_lng = lng;
	}

	@JSON
	public String getImageTitle() {
		if (_imageTitle == null) {
			return StringPool.BLANK;
		}
		else {
			return _imageTitle;
		}
	}

	public void setImageTitle(String imageTitle) {
		_imageTitle = imageTitle;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(0,
			Garden.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public Garden toEscapedModel() {
		if (_escapedModelProxy == null) {
			_escapedModelProxy = (Garden)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelProxyInterfaces,
					new AutoEscapeBeanHandler(this));
		}

		return _escapedModelProxy;
	}

	@Override
	public Object clone() {
		GardenImpl gardenImpl = new GardenImpl();

		gardenImpl.setGardenId(getGardenId());
		gardenImpl.setGardenFolderId(getGardenFolderId());
		gardenImpl.setGardenImageId(getGardenImageId());
		gardenImpl.setOwnerUserId(getOwnerUserId());
		gardenImpl.setCreateDate(getCreateDate());
		gardenImpl.setModifiedDate(getModifiedDate());
		gardenImpl.setName(getName());
		gardenImpl.setComment(getComment());
		gardenImpl.setLat(getLat());
		gardenImpl.setLng(getLng());
		gardenImpl.setImageTitle(getImageTitle());

		gardenImpl.resetOriginalValues();

		return gardenImpl;
	}

	public int compareTo(Garden garden) {
		int value = 0;

		value = DateUtil.compareTo(getCreateDate(), garden.getCreateDate());

		value = value * -1;

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		Garden garden = null;

		try {
			garden = (Garden)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = garden.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public void resetOriginalValues() {
	}

	@Override
	public CacheModel<Garden> toCacheModel() {
		GardenCacheModel gardenCacheModel = new GardenCacheModel();

		gardenCacheModel.gardenId = getGardenId();

		gardenCacheModel.gardenFolderId = getGardenFolderId();

		gardenCacheModel.gardenImageId = getGardenImageId();

		gardenCacheModel.ownerUserId = getOwnerUserId();

		Date createDate = getCreateDate();

		if (createDate != null) {
			gardenCacheModel.createDate = createDate.getTime();
		}
		else {
			gardenCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			gardenCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			gardenCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		gardenCacheModel.name = getName();

		String name = gardenCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			gardenCacheModel.name = null;
		}

		gardenCacheModel.comment = getComment();

		String comment = gardenCacheModel.comment;

		if ((comment != null) && (comment.length() == 0)) {
			gardenCacheModel.comment = null;
		}

		gardenCacheModel.lat = getLat();

		gardenCacheModel.lng = getLng();

		gardenCacheModel.imageTitle = getImageTitle();

		String imageTitle = gardenCacheModel.imageTitle;

		if ((imageTitle != null) && (imageTitle.length() == 0)) {
			gardenCacheModel.imageTitle = null;
		}

		return gardenCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{gardenId=");
		sb.append(getGardenId());
		sb.append(", gardenFolderId=");
		sb.append(getGardenFolderId());
		sb.append(", gardenImageId=");
		sb.append(getGardenImageId());
		sb.append(", ownerUserId=");
		sb.append(getOwnerUserId());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", comment=");
		sb.append(getComment());
		sb.append(", lat=");
		sb.append(getLat());
		sb.append(", lng=");
		sb.append(getLng());
		sb.append(", imageTitle=");
		sb.append(getImageTitle());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(37);

		sb.append("<model><model-name>");
		sb.append("net.sareweb.barazkide.model.Garden");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>gardenId</column-name><column-value><![CDATA[");
		sb.append(getGardenId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>gardenFolderId</column-name><column-value><![CDATA[");
		sb.append(getGardenFolderId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>gardenImageId</column-name><column-value><![CDATA[");
		sb.append(getGardenImageId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>ownerUserId</column-name><column-value><![CDATA[");
		sb.append(getOwnerUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>comment</column-name><column-value><![CDATA[");
		sb.append(getComment());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>lat</column-name><column-value><![CDATA[");
		sb.append(getLat());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>lng</column-name><column-value><![CDATA[");
		sb.append(getLng());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>imageTitle</column-name><column-value><![CDATA[");
		sb.append(getImageTitle());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static ClassLoader _classLoader = Garden.class.getClassLoader();
	private static Class<?>[] _escapedModelProxyInterfaces = new Class[] {
			Garden.class
		};
	private long _gardenId;
	private long _gardenFolderId;
	private long _gardenImageId;
	private long _ownerUserId;
	private String _ownerUserUuid;
	private Date _createDate;
	private Date _modifiedDate;
	private String _name;
	private String _comment;
	private double _lat;
	private double _lng;
	private String _imageTitle;
	private Garden _escapedModelProxy;
}