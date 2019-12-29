package com.rfid.demo.service.impl;

import com.rfid.demo.entity.Location;
import com.rfid.demo.entity.Reader;
import com.rfid.demo.entity.Tag;
import com.rfid.demo.service.ReaderService;
import com.rfid.demo.utils.Method;
import com.rfid.demo.utils.Parameter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReaderServiceImpl implements ReaderService {
    @Override
    public boolean checkTagForAll(List<Reader> readers, Tag tag) {
        for(Reader reader: readers){
            if(Method.locWithinReader(tag.getLocation().getX(),tag.getLocation().getY(),
                    reader.getLoc().getX(),reader.getLoc().getY(), Parameter.ri)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkTagForOne(Reader reader, Tag tag) {
        return Method.locWithinReader(tag.getLocation().getX(), tag.getLocation().getY(),
                reader.getLoc().getX(), reader.getLoc().getY(), Parameter.ri);

    }

    @Override
    public void addReader(Location loc) {
        List<Reader> readers=Parameter.readerList;
        int newId=readers.size()+1;

        Reader r=new Reader(newId,loc);
        readers.add(r);

        Parameter.readerList=readers;
        Parameter.readerNum=readers.size();
    }

    @Override
    public void editReader(Reader reader) {
        List<Reader> readers=Parameter.readerList;
        for(Reader r:readers){
            if(r.getId()==reader.getId()){
                r.setLoc(reader.getLoc());
            }
        }
        Parameter.readerList=readers;
        Parameter.readerNum=readers.size();
    }

    @Override
    public void deleteReader(int id) {
        List<Reader> readers=Parameter.readerList;
        readers.removeIf(r -> r.getId() == id);
        Parameter.readerList=readers;
        Parameter.readerNum=readers.size();
    }
}
