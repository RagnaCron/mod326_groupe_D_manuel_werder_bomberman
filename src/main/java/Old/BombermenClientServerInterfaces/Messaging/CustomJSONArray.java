package Old.BombermenClientServerInterfaces.Messaging;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONString;

import java.util.Collection;

public class CustomJSONArray extends JSONArray implements JSONString {

	public CustomJSONArray(String source) {
		super(source);
	}

	public CustomJSONArray(Collection<?> collection) {
		super(collection);
	}

	public CustomJSONArray(Object array) throws JSONException {
		super(array);
	}

	@Override
	public String toJSONString() {
		StringBuilder builder = new StringBuilder();
		forEach(value -> builder.append(value).append(" "));
		return builder.toString();
	}

//	@Override
//	public CustomJSONArray get(int index) throws JSONException{
//		return (CustomJSONArray) super.get(index);
//	}
//
//	@Override
//	public CustomJSONArray getJSONArray(int index) throws JSONException {
//		return (CustomJSONArray) super.getJSONArray(index);
//	}
//
//	@Override
//	public int getInt(int index) throws JSONException {
//		return super.getInt(index);
//	}
}
