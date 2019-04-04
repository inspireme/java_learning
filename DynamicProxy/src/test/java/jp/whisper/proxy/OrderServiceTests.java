package jp.whisper.proxy;

import java.lang.reflect.Proxy;

import org.junit.Test;

import jp.whisper.proxy.extend.MyClassLoader;
import jp.whisper.proxy.extend.MyInvocationHandler;
import jp.whisper.proxy.extend.OrderServiceProxy;
import jp.whisper.proxy.original.OrderServiceHandler;
import jp.whisper.proxy.service.OrderService;
import jp.whisper.proxy.service.impl.OrderServiceImpl;

public class OrderServiceTests {

	@Test
	public void testFix() {
		OrderService orderService = new OrderServiceImpl();
		OrderServiceHandler orderServiceHandler = new OrderServiceHandler(orderService);

		//プロクシを生成する
		OrderService proxy = (OrderService) Proxy.newProxyInstance(orderService.getClass().getClassLoader(), new Class[] {OrderService.class}, orderServiceHandler);

		System.out.println(proxy.getClass().getName());

		System.out.println(proxy.fix("OP0000001"));

		//JVMに生成されたプロクシクラスをファイルに出力する
		Utils.printClassFile("$Proxy9", OrderService.class);
	}

	@Test
	public void testFix2() {
		OrderService orderService = new OrderServiceImpl();
		MyInvocationHandler myInvocationHandler = new jp.whisper.proxy.extend.OrderServiceHandler(orderService);

		OrderService proxy = (OrderService) OrderServiceProxy.newProxyInstance(new MyClassLoader("D:\\01.Myspace\\java_learning\\DynamicProxy\\src\\main\\java\\jp\\whisper\\proxy\\extend\\", "jp.whisper.proxy.extend"), OrderService.class, myInvocationHandler);

		System.out.println(proxy.getClass().getName());

		System.out.println(proxy.fix("OP0000002"));
	}


}
