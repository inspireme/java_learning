package jp.whisper.proxy.original;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import jp.whisper.proxy.service.OrderService;

public class OrderServiceHandler implements InvocationHandler {
	private OrderService orderService;

	public OrderServiceHandler(OrderService orderService) {
		this.orderService = orderService;
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		this.before();
		Object slipNum = method.invoke(this.orderService, args);
		this.after();
		return slipNum;
	}

	private void before() {
		System.out.println("注文確定開始");
	}

	private void after() {
		System.out.println("注文確定完了");
	}

}
