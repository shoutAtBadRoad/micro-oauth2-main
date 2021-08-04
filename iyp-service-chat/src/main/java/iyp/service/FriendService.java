package iyp.service;

import iyp.entity.iFriend;
import org.springframework.stereotype.Service;

import java.util.List;

public interface FriendService {

    List<iFriend> getFrdList(int userId);

    boolean addFrd(int host,int frd);

}
