package com.test.dproject.epidemicdatacnr;

import java.util.List;
/**
 * Created by Tharushi on 12/18/2016.
 */
public interface RegionDAO {
    public List<Region> getAllRegions();
    public Region getRegion(String region_ID);
    public boolean updateRegion(Region region);
    public boolean deleteRegion(Region region);
}
