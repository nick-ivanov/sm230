/****************************************************************************
 *    sm230 -- Non-traditional Forex Research Tool
 *    Copyright (C) 2017  Nick Ivanov
 *
 *    This program is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation, either version 3 of the License, or
 *    (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 *    E-mail: nick@nnbits.org
 *    Website: http://nnbits.org/sm230
 *****************************************************************************/

import org.apache.http.client.ClientProtocolException;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public interface ProviderInterface {
    // TODO: Add a docstring/comment in here
    void initProvider(String propertiesFile, String username, String password) throws Exception;

    // TODO: docstring/comment
    ArrayList<Tick> getTicks(String instrument, long beginTimestamp, long endTimestamp);

    // TODO: docstring/comment
    ArrayList<SM230Candle> getRecentCandles(String instrument, String granularity, int count) throws ClientProtocolException,IOException;

    // TODO: docstring/comment
    ArrayList<String> getGranularitiesAvailable();

    // TODO: docstring/comment
    ArrayList<String> getInstrumentsAvailable();

    // TODO: docstring/comment
    int getMaxCandles(String instrument);
}
