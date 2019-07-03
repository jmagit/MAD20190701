package com.example.demo;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
public class MeGustaService {
	public final String ME_GUSTA_CONT = "megusta";
	@Autowired
	private StringRedisTemplate template;
	private ValueOperations<String, String> redisValue;

	@PostConstruct
	private void inicializa() {
		redisValue = template.opsForValue();
		if (redisValue.get(ME_GUSTA_CONT) == null) {
			redisValue.set(ME_GUSTA_CONT, "0");
			template.expire(ME_GUSTA_CONT, 1L, TimeUnit.HOURS);
		}
	}

	private String get() {
		return "Llevas " + redisValue.get(ME_GUSTA_CONT);
	}

	public String add() {
		return "Llevas " + redisValue.increment(ME_GUSTA_CONT);
	}

	public String add(int id) {
		long r = 0;
		Date ini = new Date();
		for (int i = 0; i++ < id * 1000; r = redisValue.increment(ME_GUSTA_CONT))
			;
		return "Llevas " + r + ". Tardo " + ((new Date()).getTime() - ini.getTime()) + " ms.";
	}

}
