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

package net.sareweb.barazkide.service.impl;

import java.util.Date;
import java.util.List;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.Criterion;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Order;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.auth.PrincipalException;

import net.sareweb.barazkide.model.Garden;
import net.sareweb.barazkide.service.GardenLocalServiceUtil;
import net.sareweb.barazkide.service.base.GardenServiceBaseImpl;

/**
 * The implementation of the garden remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link net.sareweb.barazkide.service.GardenService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author A.Galdos
 * @see net.sareweb.barazkide.service.base.GardenServiceBaseImpl
 * @see net.sareweb.barazkide.service.GardenServiceUtil
 */
public class GardenServiceImpl extends GardenServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link net.sareweb.barazkide.service.GardenServiceUtil} to access the garden remote service.
	 */
	
	public Garden addGarden(String name, String comment, double lat, double lng, long gardenImageId) throws SystemException, PrincipalException{
		Garden garden = GardenLocalServiceUtil.createGarden(CounterLocalServiceUtil.increment());
		
		garden.setOwnerUserId(getGuestOrUserId());
		garden.setCreateDate(new Date());
		garden.setModifiedDate(new Date());
		garden.setName(name);
		garden.setComment(comment);
		garden.setLat(lat);
		garden.setLng(lng);
		garden.setGardenImageId(gardenImageId);
		
		return garden;
	}
	
	public List<Garden> getGardens() throws SystemException{
		return GardenLocalServiceUtil.getGardens(-1, -1);
	}
	
	@SuppressWarnings("unchecked")
	public List<Garden> getGardensFromDate(long date, boolean ascending) throws SystemException{
		DynamicQuery dq = DynamicQueryFactoryUtil.forClass(Garden.class);
		Criterion dateCr = null;
		Order order = null;
		if(ascending){
			dateCr = PropertyFactoryUtil.forName("createDate").ge(new Date(date));
			order = OrderFactoryUtil.asc("createDate");
		}
		else{
			dateCr = PropertyFactoryUtil.forName("createDate").le(new Date(date));
			order = OrderFactoryUtil.desc("createDate");
		}
		dq.add(dateCr);
		dq.addOrder(order);
		return GardenLocalServiceUtil.dynamicQuery(dq);
	}
	
	
}