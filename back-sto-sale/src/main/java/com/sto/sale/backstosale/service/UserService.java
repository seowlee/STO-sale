package com.sto.sale.backstosale.service;

import com.sto.sale.backstosale.repository.UserRepository;

import java.util.List;

public class UserService {
	private UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	/**
	 * 모든 userId 목록 조회
	 */
	public List<Long> findAllUserIds() {
		return userRepository.findAllByUserIds();
	}
}
