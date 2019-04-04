package jp.whisper.proxy.service.impl;

import jp.whisper.proxy.service.OrderService;

public class OrderServiceImpl implements OrderService {

	public String fix(String slipNum) {
		System.out.println(String.format("注文確定処理中・・・・・注文番号：%s", slipNum));
		return slipNum;
	}

}
