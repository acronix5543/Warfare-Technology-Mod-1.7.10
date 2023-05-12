package com.wartec.wartecmod.savedata.satellites;

import com.wartec.wartecmod.items.wartecmodItems;

public class SatelliteRegistry {
	public static void main(String[] args) {
		SatelliteNuclear Satellite1 = new SatelliteNuclear();
		Satellite1.registerSatellite(SatelliteNuclear.class, wartecmodItems.sat_nuclear);
		SatelliteEmp Satellite2 = new SatelliteEmp();
		Satellite2.registerSatellite(SatelliteEmp.class, wartecmodItems.sat_emp);
		};
}
