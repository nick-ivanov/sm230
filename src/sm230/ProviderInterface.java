//    Copyright (C) Nick Ivanov <nick@nnbits.org> <nnrowan@gmail.com>
//    All rights reserved.

package sm230;

import java.util.ArrayList;

public interface ProviderInterface {
    // TODO: Add a docstring/comment in here
    void initProvider(String propertiesFile, String username, String password) throws Exception;

    // TODO: docstring/comment
    ArrayList<Tick> getTicks(String instrument, long beginTimestamp, long endTimestamp);
}
