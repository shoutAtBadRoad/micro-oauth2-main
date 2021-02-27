package com.post.db.upservice;

import com.post.db.entities.Pack;
import com.post.db.entities.PackStored;

public interface BusinessService {

    int putPackInStation(Pack pack);

    int putPackOnShelf(PackStored packStored);

    int getPackOffShelf(String packId);

}
