package Interfaces;

import org.json.JSONObject;

public interface InterfazJson<T>
{
    JSONObject toJSON();

    T fromJSON(JSONObject objeto);

    int getID();
}
