package com.example.util;

import com.google.common.collect.Lists;
import org.springframework.cglib.beans.BeanCopier;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * cglib 实现拷贝，基础简单类型 效率高
 *
 * 注： (1)相同属性名，且类型不匹配时候的处理，ok，但是未满足的属性不拷贝； (2)get和set方法不匹配的处理，创建拷贝的时候报错，无法拷贝任何属性(当且仅当sourceClass的get方法超过set方法时出现) (3)BeanCopier 初始化例子：BeanCopier
 * copier = BeanCopier.create(Source.class, Target.class, useConverter=true) 第三个参数userConverter,是否开启Convert,默认BeanCopier只会做同名，同类型属性的copier,否则就会报错.
 * copier = BeanCopier.create(source.getClass(), target.getClass(), false); copier.copy(source, target, null); (4)修复beanCopier对set方法强限制的约束
 * 改写net.sf.cglib.beans.BeanCopier.Generator.generateClass(ClassVisitor)方法 将133行的 MethodInfo write =
 * ReflectUtils.getMethodInfo(setter.getWriteMethod()); 预先存一个names2放入 Map names2 = new HashMap(); for (int i = 0; i < getters.length; ++i) {
 * names2.put(setters[i].getName(), getters[i]); } 调用这行代码前判断查询下，如果没有改writeMethod则忽略掉该字段的操作，这样就可以避免异常的发生。
 *
 * @author hmh
 */
public class BeanCopierUtils {
    // 实例缓存
    public static Map<String, BeanCopier> beanCopierMap = new HashMap<String, BeanCopier>();

    /***
     * 拷贝属性
     * @param source
     * @param target
     */
    public static void copy(Object source, Object target) {
        String beanKey = getGenerateKey(source.getClass(), target.getClass());
        BeanCopier copier = getBeanCopier(beanKey, source.getClass(), target.getClass());
        // 拷贝属性source-->target
        copier.copy(source, target, null);
    }

    public static <T> T copy(Object source, Class<T> destinationClass) throws InstantiationException, IllegalAccessException {
        String beanKey = getGenerateKey(source.getClass(), destinationClass);
        BeanCopier copier = getBeanCopier(beanKey, source.getClass(), destinationClass);
        T target = destinationClass.newInstance();

        // 拷贝属性source-->target
        copier.copy(source, target, null);
        return target;
    }

    @SuppressWarnings("rawtypes")
    public static <T> List<T> copyList(Collection sourceList, Class<T> destinationClass) throws InstantiationException, IllegalAccessException {
        List<T> destinationList = Lists.newArrayList();
        if (sourceList == null || sourceList.size() == 0) { // 元数据为空
            return destinationList;
        }

        //
        for (Object sourceObject : sourceList) {
            T destinationObject = copy(sourceObject, destinationClass);
            destinationList.add(destinationObject);
        }
        return destinationList;
    }

    @SuppressWarnings("rawtypes")
    private static BeanCopier getBeanCopier(String beanKey, Class sourceClass, Class targetClass) {
        BeanCopier copier = null;
        if (!beanCopierMap.containsKey(beanKey)) {
            copier = BeanCopier.create(sourceClass, targetClass, false);
            beanCopierMap.put(beanKey, copier);
        } else {
            copier = beanCopierMap.get(beanKey);
        }
        return copier;
    }

    private static String getGenerateKey(Class<?> class1, Class<?> class2) {
        return class1.toString() + class2.toString();
    }
}