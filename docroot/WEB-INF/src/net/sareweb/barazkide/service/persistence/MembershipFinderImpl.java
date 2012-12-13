package net.sareweb.barazkide.service.persistence;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sareweb.barazkide.model.Garden;
import net.sareweb.barazkide.model.Membership;
import net.sareweb.barazkide.model.impl.GardenImpl;

import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.support.tomcat.loader.PortalClassLoaderFactory;
import com.liferay.util.dao.orm.CustomSQLUtil;

public class MembershipFinderImpl extends BasePersistenceImpl<Membership> implements MembershipFinder {
	
	public static String FIND_MEMBER_USERS = MembershipFinder.class
			.getName() + ".findMemberUsers";
	
}