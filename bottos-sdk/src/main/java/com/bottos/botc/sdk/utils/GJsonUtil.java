package com.bottos.botc.sdk.utils;

import android.util.JsonReader;
import android.util.JsonToken;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.LongSerializationPolicy;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Json数据解析
 *
 * @author xionglh
 * @version GJsonUtil.java 2014年10月1日 上午8:39:04 v1.0.0 xionglihui
 */
public class GJsonUtil {

	private static Gson gson = new Gson();
//	private static Gson gson = new GsonBuilder()
//			.excludeFieldsWithoutExposeAnnotation().setLongSerializationPolicy(LongSerializationPolicy.STRING).create();

	/**
	 * json转化成 java bean
	 *
	 * @param json
	 * @return java bean of beanClass
	 */
	@SuppressWarnings({ "unchecked" })
	public static <T> T toObject(String json, Type type) {
		if (json == null) {
			return null;
		}
		if (type == null) {
			return null;
		}
		Gson gsonMap = new Gson();
		T object;
		try {
			object = (T) gsonMap.fromJson(json, type);
		} catch (RuntimeException e) {
			throw e;
		}
		return object;
	}

	/**
	 * json转化成 java bean
	 *
	 * @param json
	 * @param beanClass
	 * @return java bean of beanClass
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> T toObject(String json, Class beanClass) {
		if (json == null) {
			return null;
		}
		if (beanClass == null) {
			return null;
		}

		@SuppressWarnings("unused")
		T object;
		try {
			// json = URLDecoder.decode(json, "UTF-8");
			return (T) gson.fromJson(json, beanClass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * java bean 转json
	 *
	 * @param object
	 *            java bean
	 * @param beanClass
	 *            需要转化成json的bean对象的class
	 * @return json string
	 */
	@SuppressWarnings("rawtypes")
	public static String toJson(Object object, Class beanClass) {
		if (object == null) {
			return null;
		}
		if (beanClass == null) {
			return null;
		}
		return gson.toJson(object, beanClass);
	}

	/**
	 * java bean 转json 排除属性
	 *
	 * @param object
	 *            java bean
	 * @param beanClass
	 *            需要转化成json的bean对象的class
	 * @return json string
	 */
	@SuppressWarnings("rawtypes")
	public static String toJsonExp(Object object, Class beanClass) {
		if (object == null) {
			return null;
		}
		if (beanClass == null) {
			return null;
		}
		return gson.toJson(object, beanClass);
	}

	/**
	 * Map转json数据
	 *
	 * @param entity
	 *            HashMap
	 * @return json strings
	 */
	public static String mapToJson(Map<String, Object> entity) {
		if (entity == null)
			return null;
		return gson.toJson(entity, HashMap.class);
	}

	/**
	 * Map转json数据
	 *
	 * @param obj
	 *            HashMap
	 * @return json strings
	 */
	public static String objToJson(Object obj) {
		if (obj == null)
			return null;
		return gson.toJson(obj);
	}

	@SuppressWarnings("unchecked")
	public static Map<String, Object> toMap(String json) {
		return gson.fromJson(json, Map.class);
	}

//	 static class  GsonTypeAdapter extends TypeAdapter<Object>
//	{
//
//        @Override
//        public void write(JsonWriter out, Object value) throws IOException {
//
//        }
//
//        @Override
//        public Object read(com.google.gson.stream.JsonReader in) throws IOException {
//            // 反序列化
//            com.google.gson.stream.JsonToken token = in.peek();
//            switch (token)
//            {
//                case BEGIN_ARRAY:
//
//                    List<Object> list = new ArrayList<>();
//                    in.beginArray();
//                    while (in.hasNext())
//                    {
//                        list.add(read(in));
//                    }
//                    in.endArray();
//                    return list;
//
//                case BEGIN_OBJECT:
//
//                    Map<String, Object> map = new HashMap<String, Object>();
//                    in.beginObject();
//                    while (in.hasNext())
//                    {
//                        map.put(in.nextName(), read(in));
//                    }
//                    in.endObject();
//                    return map;
//
//                case STRING:
//
//                    return in.nextString();
//
//                case NUMBER:
//
//                    /**
//                     * 改写数字的处理逻辑，将数字值分为整型与浮点型。
//                     */
//                    double dbNum = in.nextDouble();
//
//                    // 数字超过long的最大值，返回浮点类型
//                    if (dbNum > Long.MAX_VALUE)
//                    {
//                        return dbNum;
//                    }
//
//                    // 判断数字是否为整数值
//                    long lngNum = (long) dbNum;
//                    if (dbNum == lngNum)
//                    {
//                        return lngNum;
//                    } else
//                    {
//                        return dbNum;
//                    }
//
//                case BOOLEAN:
//                    return in.nextBoolean();
//
//                case NULL:
//                    in.nextNull();
//                    return null;
//
//                default:
//                    throw new IllegalStateException();
//            }        }
//    }
}
