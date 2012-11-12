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

package net.sareweb.barazkide.service.http;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import net.sareweb.barazkide.service.GardenServiceUtil;

import java.rmi.RemoteException;

/**
 * <p>
 * This class provides a SOAP utility for the
 * {@link net.sareweb.barazkide.service.GardenServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 * </p>
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link net.sareweb.barazkide.model.GardenSoap}.
 * If the method in the service utility returns a
 * {@link net.sareweb.barazkide.model.Garden}, that is translated to a
 * {@link net.sareweb.barazkide.model.GardenSoap}. Methods that SOAP cannot
 * safely wire are skipped.
 * </p>
 *
 * <p>
 * The benefits of using the SOAP utility is that it is cross platform
 * compatible. SOAP allows different languages like Java, .NET, C++, PHP, and
 * even Perl, to call the generated services. One drawback of SOAP is that it is
 * slow because it needs to serialize all calls into a text format (XML).
 * </p>
 *
 * <p>
 * You can see a list of services at
 * http://localhost:8080/api/secure/axis. Set the property
 * <b>axis.servlet.hosts.allowed</b> in portal.properties to configure
 * security.
 * </p>
 *
 * <p>
 * The SOAP utility is only generated for remote services.
 * </p>
 *
 * @author    A.Galdos
 * @see       GardenServiceHttp
 * @see       net.sareweb.barazkide.model.GardenSoap
 * @see       net.sareweb.barazkide.service.GardenServiceUtil
 * @generated
 */
public class GardenServiceSoap {
	public static net.sareweb.barazkide.model.GardenSoap addGarden(
		java.lang.String name, java.lang.String comment, double lat,
		double lng, long gardenImageId) throws RemoteException {
		try {
			net.sareweb.barazkide.model.Garden returnValue = GardenServiceUtil.addGarden(name,
					comment, lat, lng, gardenImageId);

			return net.sareweb.barazkide.model.GardenSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static net.sareweb.barazkide.model.GardenSoap[] getGardens()
		throws RemoteException {
		try {
			java.util.List<net.sareweb.barazkide.model.Garden> returnValue = GardenServiceUtil.getGardens();

			return net.sareweb.barazkide.model.GardenSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static net.sareweb.barazkide.model.GardenSoap[] getGardensFromDate(
		long date, boolean ascending) throws RemoteException {
		try {
			java.util.List<net.sareweb.barazkide.model.Garden> returnValue = GardenServiceUtil.getGardensFromDate(date,
					ascending);

			return net.sareweb.barazkide.model.GardenSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(GardenServiceSoap.class);
}