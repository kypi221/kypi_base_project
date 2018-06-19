package com.kypi.demoproject.data.repository;

import com.kypi.demoproject.domain.entities.DemoObject;
import com.kypi.demoproject.domain.repository.DemoRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DemoRepositoryImpl implements DemoRepository {

    private final DemoObject demoObject;

    @Inject
    public DemoRepositoryImpl() {
        this.demoObject = new DemoObject("My Demo Object");
    }

    @Override
    public DemoObject getDemoObject() {
        return demoObject;
    }
}
