package org.dragard.projectmanager.api.domain;

import java.io.IOException;

public interface Domain {

    void save() throws IOException;

    void load() throws IOException, ClassNotFoundException;
}
