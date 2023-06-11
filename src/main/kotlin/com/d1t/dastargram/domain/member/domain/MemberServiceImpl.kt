package com.d1t.dastargram.domain.member.domain

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class MemberServiceImpl(val memberStore: MemberStore, val memberReader: MemberReader): MemberService {
}