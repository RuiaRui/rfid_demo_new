package com.rfid.demo.service;

import com.rfid.demo.entity.Location;
import com.rfid.demo.entity.Reader;
import com.rfid.demo.entity.Tag;

import java.util.List;

public interface ReaderService {

    boolean checkTagForAll(List<Reader> readerlist, Tag tag);
    boolean checkTagForOne(Reader reader,Tag tag);

    void addReader(Location loc);
    void editReader(Reader reader);
    void deleteReader(int id);

}
