package pl.edu.wszib.project.forum.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.project.forum.dao.IDBPopulateDAO;
import pl.edu.wszib.project.forum.services.IDBPopulateService;

@Service
public class DBPopulateServiceImpl implements IDBPopulateService {
    @Autowired
    IDBPopulateDAO dbPopulateDAO;

    @Override
    public void dbPopulate() {
        this.dbPopulateDAO.populateFromSQLDump();
    }
}
