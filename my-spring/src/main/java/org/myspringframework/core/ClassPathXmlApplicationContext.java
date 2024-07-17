package org.myspringframework.core;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassPathXmlApplicationContext implements ApplicationContext{
    private Map<String,Object> singletonObjects = new HashMap<>();

    public ClassPathXmlApplicationContext(String configLocation) {
        // 单例模式下 一旦ApplicationContext new出来
        // 所有对象都创建好了
        // 所以应该是在构造函数里面干这个事

        // 在底层解析配置文件，把对象都new出来
        // 注意：配置文件应放到类路径下
        // 三级缓存 对应三个 Map
        // key存储id  value存储对象

        // 声明dom4j解析XML文件的核心对象
        SAXReader reader = new SAXReader();

        // 获取输入流 指向配置文件
        InputStream in = ClassLoader.getSystemClassLoader().getResourceAsStream(configLocation);

        // 读文件
        try {
            Document document = reader.read(in);
            // 获取所有的bean标签
            List<Node> beans = document.selectNodes("//bean");

            // 遍历所有的bean标签
            beans.forEach(bean -> {
                // 向下转型（为了使用element接口里更加丰富的方法）
                Element beanEle = (Element) bean;

                // 获取id属性
                String id = beanEle.attributeValue("id");
                // 获取类名
                String className = beanEle.attributeValue("class");

                // 通过反射机制创建对象，将其放到Map集合中，提前曝光
                try {
                    // 1.先通过forName拿到class
                    Class<?> clazz = Class.forName(className);
                    // 2.通过class获取对象的构造函数
                    Constructor<?> defaultCon = clazz.getDeclaredConstructor();
                    // 3.通过构造函数实例化对象
                    Object beanInstance = defaultCon.newInstance();
                    // 4.将bean提前曝光 加入Map集合
                    singletonObjects.put(id,beanInstance);
//                    // 记录日志
//                    logger.info(singletonObjects.toString());
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (NoSuchMethodException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                } catch (InstantiationException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            });

            // 再次重新把所有的bean标签遍历，这一次主要是给对象的属性赋值
            beans.forEach(bean -> {
                Element beanEle = (Element) bean;
                // 获取id
                String id = beanEle.attributeValue("id");
                // 获取类名
                String className = beanEle.attributeValue("class");

                // 获取改bean标签下 所有的property标签
                List<Element> properties = beanEle.elements("property");
                // 遍历所有的属性标签
                properties.forEach(property -> {
                    // 获取属性名
                    String propertyName = property.attributeValue("name");

                    //  获取set方法名
                    String setMethodName = "set" + propertyName.toUpperCase().charAt(0) + propertyName.substring(1);

                    // 获取set方法 反射机制
                    try {
                        Class<?> clazz = Class.forName(className);

                        // getDeclaredMethod中参数的类型就是property的类型
                        // 可以通过属性名 获取到 属性类型：
                        Field field = clazz.getDeclaredField(propertyName);
                        // 通过方法名 和 参数 获取到对应的set方法
                        Method setMethod = clazz.getDeclaredMethod(setMethodName, field.getType());
                        // 调用set方法 指定调用set方法的对象，和需要set的值

                        // 值可以是value 也可以是ref
                        // 获取具体的值
                        String value = property.attributeValue("value");
                        String ref = property.attributeValue("ref");
                        Object actualValue = null;
                        if (value != null) {
                            // 说明这个值是简单类型
                            // 这个value 还要考虑类型转换
                            // myspring框架声明：
                            // 只支持这些类型为简单类型
                            // byte short int long float double boolean char
                            // Byte Short Integer Long Float Double Boolean Character
                            // String
                            // 1. 获取属性的类型名
//                            field.getType().getName() // 注：这么写是错误的 得到的是带包名的 java.lang.String
                            String propertyTypeSimpleName = field.getType().getSimpleName(); //得到的是 String

                            /**
                             * 最笨的方法 一个一个匹配
                             */
                            switch (propertyTypeSimpleName) {
                                case "byte":
                                    actualValue = Byte.parseByte(value);
                                    break;
                                case "short":
                                    actualValue = Short.parseShort(value);
                                    break;
                                case "int":
                                    actualValue = Integer.parseInt(value);
                                    break;
                                case "long":
                                    actualValue = Long.parseLong(value);
                                    break;
                                case "float":
                                    actualValue = Float.parseFloat(value);
                                    break;
                                case "double":
                                    actualValue = Double.parseDouble(value);
                                    break;
                                case "boolean":
                                    actualValue = Boolean.parseBoolean(value);
                                    break;
                                case "char":
                                    actualValue = value.charAt(0);
                                    break;
                                case "Byte":
                                    actualValue = Byte.valueOf(value);
                                    break;
                                case "Short":
                                    actualValue = Short.valueOf(value);
                                    break;
                                case "Integer":
                                    actualValue = Integer.valueOf(value);
                                    break;
                                case "Long":
                                    actualValue = Long.valueOf(value);
                                    break;
                                case "Float":
                                    actualValue = Float.valueOf(value);
                                    break;
                                case "Double":
                                    actualValue = Double.valueOf(value);
                                    break;
                                case "Boolean":
                                    actualValue = Boolean.valueOf(value);
                                    break;
                                case "Character":
                                    actualValue = value.charAt(0);
                                    break;
                                case "String":
                                    actualValue = value;

                            }
                            setMethod.invoke(singletonObjects.get(id),actualValue);
                        }
                        if (ref != null) {
                            // 说明这个值是非简单类型
                            setMethod.invoke(singletonObjects.get(id),singletonObjects.get(ref));
                        }

                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    } catch (NoSuchFieldException e) {
                        throw new RuntimeException(e);
                    } catch (NoSuchMethodException e) {
                        throw new RuntimeException(e);
                    } catch (InvocationTargetException e) {
                        throw new RuntimeException(e);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }

                });
            });
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }




    }

    @Override
    public Object getBean(String beanName) {
        return singletonObjects.get(beanName);
    }
}
