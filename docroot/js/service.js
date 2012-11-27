Liferay.Service.register("Liferay.Service.Barazkide", "net.sareweb.barazkide.service", "Barazkide-portlet");

Liferay.Service.registerClass(
	Liferay.Service.Barazkide, "Garden",
	{
		addGarden: true,
		getGardens: true,
		getUserGardensFromDate: true
	}
);