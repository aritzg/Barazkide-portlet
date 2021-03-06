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

import net.sareweb.barazkide.model.Badge;
import net.sareweb.barazkide.model.BadgeModel;
import net.sareweb.barazkide.model.BadgeSoap;

import java.io.Serializable;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The base model implementation for the Badge service. Represents a row in the &quot;Barazkide_Badge&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link net.sareweb.barazkide.model.BadgeModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link BadgeImpl}.
 * </p>
 *
 * @author A.Galdos
 * @see BadgeImpl
 * @see net.sareweb.barazkide.model.Badge
 * @see net.sareweb.barazkide.model.BadgeModel
 * @generated
 */
@JSON(strict = true)
public class BadgeModelImpl extends BaseModelImpl<Badge> implements BadgeModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a badge model instance should use the {@link net.sareweb.barazkide.model.Badge} interface instead.
	 */
	public static final String TABLE_NAME = "Barazkide_Badge";
	public static final Object[][] TABLE_COLUMNS = {
			{ "badgeId", Types.BIGINT },
			{ "badgeType", Types.VARCHAR },
			{ "badgeTextKey", Types.VARCHAR },
			{ "badgeImageUrl", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP }
		};
	public static final String TABLE_SQL_CREATE = "create table Barazkide_Badge (badgeId LONG not null primary key,badgeType VARCHAR(75) null,badgeTextKey VARCHAR(75) null,badgeImageUrl VARCHAR(75) null,createDate DATE null)";
	public static final String TABLE_SQL_DROP = "drop table Barazkide_Badge";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.entity.cache.enabled.net.sareweb.barazkide.model.Badge"),
			false);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.net.sareweb.barazkide.model.Badge"),
			false);
	public static final boolean COLUMN_BITMASK_ENABLED = false;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static Badge toModel(BadgeSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		Badge model = new BadgeImpl();

		model.setBadgeId(soapModel.getBadgeId());
		model.setBadgeType(soapModel.getBadgeType());
		model.setBadgeTextKey(soapModel.getBadgeTextKey());
		model.setBadgeImageUrl(soapModel.getBadgeImageUrl());
		model.setCreateDate(soapModel.getCreateDate());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<Badge> toModels(BadgeSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<Badge> models = new ArrayList<Badge>(soapModels.length);

		for (BadgeSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.net.sareweb.barazkide.model.Badge"));

	public BadgeModelImpl() {
	}

	public long getPrimaryKey() {
		return _badgeId;
	}

	public void setPrimaryKey(long primaryKey) {
		setBadgeId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_badgeId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	public Class<?> getModelClass() {
		return Badge.class;
	}

	public String getModelClassName() {
		return Badge.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("badgeId", getBadgeId());
		attributes.put("badgeType", getBadgeType());
		attributes.put("badgeTextKey", getBadgeTextKey());
		attributes.put("badgeImageUrl", getBadgeImageUrl());
		attributes.put("createDate", getCreateDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long badgeId = (Long)attributes.get("badgeId");

		if (badgeId != null) {
			setBadgeId(badgeId);
		}

		String badgeType = (String)attributes.get("badgeType");

		if (badgeType != null) {
			setBadgeType(badgeType);
		}

		String badgeTextKey = (String)attributes.get("badgeTextKey");

		if (badgeTextKey != null) {
			setBadgeTextKey(badgeTextKey);
		}

		String badgeImageUrl = (String)attributes.get("badgeImageUrl");

		if (badgeImageUrl != null) {
			setBadgeImageUrl(badgeImageUrl);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}
	}

	@JSON
	public long getBadgeId() {
		return _badgeId;
	}

	public void setBadgeId(long badgeId) {
		_badgeId = badgeId;
	}

	@JSON
	public String getBadgeType() {
		if (_badgeType == null) {
			return StringPool.BLANK;
		}
		else {
			return _badgeType;
		}
	}

	public void setBadgeType(String badgeType) {
		_badgeType = badgeType;
	}

	@JSON
	public String getBadgeTextKey() {
		if (_badgeTextKey == null) {
			return StringPool.BLANK;
		}
		else {
			return _badgeTextKey;
		}
	}

	public void setBadgeTextKey(String badgeTextKey) {
		_badgeTextKey = badgeTextKey;
	}

	@JSON
	public String getBadgeImageUrl() {
		if (_badgeImageUrl == null) {
			return StringPool.BLANK;
		}
		else {
			return _badgeImageUrl;
		}
	}

	public void setBadgeImageUrl(String badgeImageUrl) {
		_badgeImageUrl = badgeImageUrl;
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
			Badge.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public Badge toEscapedModel() {
		if (_escapedModelProxy == null) {
			_escapedModelProxy = (Badge)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelProxyInterfaces,
					new AutoEscapeBeanHandler(this));
		}

		return _escapedModelProxy;
	}

	@Override
	public Object clone() {
		BadgeImpl badgeImpl = new BadgeImpl();

		badgeImpl.setBadgeId(getBadgeId());
		badgeImpl.setBadgeType(getBadgeType());
		badgeImpl.setBadgeTextKey(getBadgeTextKey());
		badgeImpl.setBadgeImageUrl(getBadgeImageUrl());
		badgeImpl.setCreateDate(getCreateDate());

		badgeImpl.resetOriginalValues();

		return badgeImpl;
	}

	public int compareTo(Badge badge) {
		long primaryKey = badge.getPrimaryKey();

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

		Badge badge = null;

		try {
			badge = (Badge)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = badge.getPrimaryKey();

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
	public CacheModel<Badge> toCacheModel() {
		BadgeCacheModel badgeCacheModel = new BadgeCacheModel();

		badgeCacheModel.badgeId = getBadgeId();

		badgeCacheModel.badgeType = getBadgeType();

		String badgeType = badgeCacheModel.badgeType;

		if ((badgeType != null) && (badgeType.length() == 0)) {
			badgeCacheModel.badgeType = null;
		}

		badgeCacheModel.badgeTextKey = getBadgeTextKey();

		String badgeTextKey = badgeCacheModel.badgeTextKey;

		if ((badgeTextKey != null) && (badgeTextKey.length() == 0)) {
			badgeCacheModel.badgeTextKey = null;
		}

		badgeCacheModel.badgeImageUrl = getBadgeImageUrl();

		String badgeImageUrl = badgeCacheModel.badgeImageUrl;

		if ((badgeImageUrl != null) && (badgeImageUrl.length() == 0)) {
			badgeCacheModel.badgeImageUrl = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			badgeCacheModel.createDate = createDate.getTime();
		}
		else {
			badgeCacheModel.createDate = Long.MIN_VALUE;
		}

		return badgeCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{badgeId=");
		sb.append(getBadgeId());
		sb.append(", badgeType=");
		sb.append(getBadgeType());
		sb.append(", badgeTextKey=");
		sb.append(getBadgeTextKey());
		sb.append(", badgeImageUrl=");
		sb.append(getBadgeImageUrl());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(19);

		sb.append("<model><model-name>");
		sb.append("net.sareweb.barazkide.model.Badge");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>badgeId</column-name><column-value><![CDATA[");
		sb.append(getBadgeId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>badgeType</column-name><column-value><![CDATA[");
		sb.append(getBadgeType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>badgeTextKey</column-name><column-value><![CDATA[");
		sb.append(getBadgeTextKey());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>badgeImageUrl</column-name><column-value><![CDATA[");
		sb.append(getBadgeImageUrl());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static ClassLoader _classLoader = Badge.class.getClassLoader();
	private static Class<?>[] _escapedModelProxyInterfaces = new Class[] {
			Badge.class
		};
	private long _badgeId;
	private String _badgeType;
	private String _badgeTextKey;
	private String _badgeImageUrl;
	private Date _createDate;
	private Badge _escapedModelProxy;
}