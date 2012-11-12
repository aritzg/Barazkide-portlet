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
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import net.sareweb.barazkide.model.GardenImage;
import net.sareweb.barazkide.model.GardenImageModel;
import net.sareweb.barazkide.model.GardenImageSoap;

import java.io.Serializable;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The base model implementation for the GardenImage service. Represents a row in the &quot;Barazkide_GardenImage&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link net.sareweb.barazkide.model.GardenImageModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link GardenImageImpl}.
 * </p>
 *
 * @author A.Galdos
 * @see GardenImageImpl
 * @see net.sareweb.barazkide.model.GardenImage
 * @see net.sareweb.barazkide.model.GardenImageModel
 * @generated
 */
@JSON(strict = true)
public class GardenImageModelImpl extends BaseModelImpl<GardenImage>
	implements GardenImageModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a garden image model instance should use the {@link net.sareweb.barazkide.model.GardenImage} interface instead.
	 */
	public static final String TABLE_NAME = "Barazkide_GardenImage";
	public static final Object[][] TABLE_COLUMNS = {
			{ "gardenImageId", Types.BIGINT },
			{ "gardenId", Types.BIGINT },
			{ "imgId", Types.BIGINT },
			{ "imgName", Types.VARCHAR },
			{ "imgUrl", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP }
		};
	public static final String TABLE_SQL_CREATE = "create table Barazkide_GardenImage (gardenImageId LONG not null primary key,gardenId LONG,imgId LONG,imgName VARCHAR(75) null,imgUrl VARCHAR(75) null,createDate DATE null)";
	public static final String TABLE_SQL_DROP = "drop table Barazkide_GardenImage";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.entity.cache.enabled.net.sareweb.barazkide.model.GardenImage"),
			false);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.net.sareweb.barazkide.model.GardenImage"),
			false);
	public static final boolean COLUMN_BITMASK_ENABLED = false;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static GardenImage toModel(GardenImageSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		GardenImage model = new GardenImageImpl();

		model.setGardenImageId(soapModel.getGardenImageId());
		model.setGardenId(soapModel.getGardenId());
		model.setImgId(soapModel.getImgId());
		model.setImgName(soapModel.getImgName());
		model.setImgUrl(soapModel.getImgUrl());
		model.setCreateDate(soapModel.getCreateDate());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<GardenImage> toModels(GardenImageSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<GardenImage> models = new ArrayList<GardenImage>(soapModels.length);

		for (GardenImageSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.net.sareweb.barazkide.model.GardenImage"));

	public GardenImageModelImpl() {
	}

	public long getPrimaryKey() {
		return _gardenImageId;
	}

	public void setPrimaryKey(long primaryKey) {
		setGardenImageId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_gardenImageId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	public Class<?> getModelClass() {
		return GardenImage.class;
	}

	public String getModelClassName() {
		return GardenImage.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("gardenImageId", getGardenImageId());
		attributes.put("gardenId", getGardenId());
		attributes.put("imgId", getImgId());
		attributes.put("imgName", getImgName());
		attributes.put("imgUrl", getImgUrl());
		attributes.put("createDate", getCreateDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long gardenImageId = (Long)attributes.get("gardenImageId");

		if (gardenImageId != null) {
			setGardenImageId(gardenImageId);
		}

		Long gardenId = (Long)attributes.get("gardenId");

		if (gardenId != null) {
			setGardenId(gardenId);
		}

		Long imgId = (Long)attributes.get("imgId");

		if (imgId != null) {
			setImgId(imgId);
		}

		String imgName = (String)attributes.get("imgName");

		if (imgName != null) {
			setImgName(imgName);
		}

		String imgUrl = (String)attributes.get("imgUrl");

		if (imgUrl != null) {
			setImgUrl(imgUrl);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}
	}

	@JSON
	public long getGardenImageId() {
		return _gardenImageId;
	}

	public void setGardenImageId(long gardenImageId) {
		_gardenImageId = gardenImageId;
	}

	@JSON
	public long getGardenId() {
		return _gardenId;
	}

	public void setGardenId(long gardenId) {
		_gardenId = gardenId;
	}

	@JSON
	public long getImgId() {
		return _imgId;
	}

	public void setImgId(long imgId) {
		_imgId = imgId;
	}

	@JSON
	public String getImgName() {
		if (_imgName == null) {
			return StringPool.BLANK;
		}
		else {
			return _imgName;
		}
	}

	public void setImgName(String imgName) {
		_imgName = imgName;
	}

	@JSON
	public String getImgUrl() {
		if (_imgUrl == null) {
			return StringPool.BLANK;
		}
		else {
			return _imgUrl;
		}
	}

	public void setImgUrl(String imgUrl) {
		_imgUrl = imgUrl;
	}

	@JSON
	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(0,
			GardenImage.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public GardenImage toEscapedModel() {
		if (_escapedModelProxy == null) {
			_escapedModelProxy = (GardenImage)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelProxyInterfaces,
					new AutoEscapeBeanHandler(this));
		}

		return _escapedModelProxy;
	}

	@Override
	public Object clone() {
		GardenImageImpl gardenImageImpl = new GardenImageImpl();

		gardenImageImpl.setGardenImageId(getGardenImageId());
		gardenImageImpl.setGardenId(getGardenId());
		gardenImageImpl.setImgId(getImgId());
		gardenImageImpl.setImgName(getImgName());
		gardenImageImpl.setImgUrl(getImgUrl());
		gardenImageImpl.setCreateDate(getCreateDate());

		gardenImageImpl.resetOriginalValues();

		return gardenImageImpl;
	}

	public int compareTo(GardenImage gardenImage) {
		long primaryKey = gardenImage.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		GardenImage gardenImage = null;

		try {
			gardenImage = (GardenImage)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = gardenImage.getPrimaryKey();

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
	public CacheModel<GardenImage> toCacheModel() {
		GardenImageCacheModel gardenImageCacheModel = new GardenImageCacheModel();

		gardenImageCacheModel.gardenImageId = getGardenImageId();

		gardenImageCacheModel.gardenId = getGardenId();

		gardenImageCacheModel.imgId = getImgId();

		gardenImageCacheModel.imgName = getImgName();

		String imgName = gardenImageCacheModel.imgName;

		if ((imgName != null) && (imgName.length() == 0)) {
			gardenImageCacheModel.imgName = null;
		}

		gardenImageCacheModel.imgUrl = getImgUrl();

		String imgUrl = gardenImageCacheModel.imgUrl;

		if ((imgUrl != null) && (imgUrl.length() == 0)) {
			gardenImageCacheModel.imgUrl = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			gardenImageCacheModel.createDate = createDate.getTime();
		}
		else {
			gardenImageCacheModel.createDate = Long.MIN_VALUE;
		}

		return gardenImageCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{gardenImageId=");
		sb.append(getGardenImageId());
		sb.append(", gardenId=");
		sb.append(getGardenId());
		sb.append(", imgId=");
		sb.append(getImgId());
		sb.append(", imgName=");
		sb.append(getImgName());
		sb.append(", imgUrl=");
		sb.append(getImgUrl());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(22);

		sb.append("<model><model-name>");
		sb.append("net.sareweb.barazkide.model.GardenImage");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>gardenImageId</column-name><column-value><![CDATA[");
		sb.append(getGardenImageId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>gardenId</column-name><column-value><![CDATA[");
		sb.append(getGardenId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>imgId</column-name><column-value><![CDATA[");
		sb.append(getImgId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>imgName</column-name><column-value><![CDATA[");
		sb.append(getImgName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>imgUrl</column-name><column-value><![CDATA[");
		sb.append(getImgUrl());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static ClassLoader _classLoader = GardenImage.class.getClassLoader();
	private static Class<?>[] _escapedModelProxyInterfaces = new Class[] {
			GardenImage.class
		};
	private long _gardenImageId;
	private long _gardenId;
	private long _imgId;
	private String _imgName;
	private String _imgUrl;
	private Date _createDate;
	private GardenImage _escapedModelProxy;
}