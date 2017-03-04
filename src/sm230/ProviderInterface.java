//    Copyright (C) Nick Ivanov <nick@nnbits.org> <nnrowan@gmail.com>
//    All rights reserved.

package sm230;

import java.util.ArrayList;

public interface ProviderInterface {
    ArrayList<Tick> getTicks(String instrument, long beginTimestamp, long endTimestamp);
}
