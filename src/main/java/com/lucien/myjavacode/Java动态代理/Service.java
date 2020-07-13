package com.lucien.myjavacode.Java动态代理;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by lucien on 2018/2/23.
 */
public interface Service {

    // function 1
    public void add();

    // function 2
    public void update();
    public static void test(){
        System.out.println("123123");
    }
}

class ServiceInstance1 implements Service{

    @Override
    public void add() {
        System.out.println("ServiceInstance1 Add ...");
    }

    @Override
    public void update() {
        System.out.println("ServiceInstance1 Update ...");
    }
}

class MyInvocationHandler implements InvocationHandler{

    //要代理的目标对象
    private Object target;

    MyInvocationHandler(){
        super();
    }
    MyInvocationHandler(Object target){
        super();
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //在程序执行前加入逻辑，MethodBeforeAdviceInterceptor
        System.out.println("MethodBeforeAdviceInterceptor before ......");
        //当代理对象调用真实对象的方法时，其会自动的跳转到代理对象关联的handler对象的invoke方法来进行调用
        Object result = method.invoke(target, args);
        //在程序执行后加入逻辑，MethodAfterAdviceInterceptor
        System.out.println("MethodAfterAdviceInterceptor after ......");
        return result;
    }
}

class Test{
    public static void main(String[] args) {
        Service service1 = new ServiceInstance1();
        MyInvocationHandler handler = new MyInvocationHandler(service1);
        /**
         * Proxy为InvocationHandler实现类动态创建一个符合某一接口的代理实例，
         * 我们用的最多的就是 newProxyInstance 这个方法
         * newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler handler)
         * loader:　　一个ClassLoader对象，定义了由哪个ClassLoader对象来对生成的代理对象进行加载
         * interfaces:　　一个Interface对象的数组，表示的是我将要给我需要代理的对象提供一组什么接口，如果我提供了一组接口给它，那么这个代理对象就宣称实现了该接口(多态)，这样我就能调用这组接口中的方法了
         * handler:　　一个InvocationHandler对象，表示的是当我这个动态代理对象在调用方法的时候，会关联到哪一个InvocationHandler对象上*/
        Service service1Proxy = (Service) Proxy.newProxyInstance(service1.getClass().getClassLoader(),
                service1.getClass().getInterfaces(), handler);  //创建代理实例
        //由动态代理生成的代理实例service1Proxy来执行方法
        service1Proxy.add();
        System.out.println();
        service1Proxy.update();
    }
}
