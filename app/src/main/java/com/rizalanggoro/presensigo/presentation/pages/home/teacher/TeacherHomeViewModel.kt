package com.rizalanggoro.presensigo.presentation.pages.home.teacher

import androidx.lifecycle.ViewModel
import com.rizalanggoro.presensigo.data.managers.TokenManager
import com.rizalanggoro.presensigo.data.repositories.BatchRepository

class TeacherHomeViewModel(
    private val batchRepository: BatchRepository,
    private val tokenManager: TokenManager
) : ViewModel() {
//    private val _uiState = MutableStateFlow(HomeState())
//    val uiState get() = _uiState.asStateFlow()

//    init {
//        getAllBatches()
//    }

//    fun testLogout() {
//        tokenManager.clear()
//    }
//
//    fun testExpireToken() {
//        val oldToken = tokenManager.get()
//        val newToken = oldToken.copy(accessToken = "hehe")
//        tokenManager.set(newToken)
//    }

//    fun getAllBatches() = viewModelScope.launch {
//        _uiState.update { it.copy(detail = HomeState.Detail.Loading) }
//        batchRepository.getAll()
//            .onLeft { result ->
//                _uiState.update {
//                    it.copy(
//                        detail = HomeState.Detail.Success(
//                            batches = result
//                        )
//                    )
//                }
//            }
//            .onRight {
//                _uiState.update { it.copy(detail = HomeState.Detail.Failure) }
//            }
//    }
}