package com.rizalanggoro.presensigo.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rizalanggoro.presensigo.data.managers.TokenManager
import com.rizalanggoro.presensigo.data.repositories.BatchRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val batchRepository: BatchRepository,
    private val tokenManager: TokenManager
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeState())
    val uiState get() = _uiState.asStateFlow()

    init {
        getAllBatches()
    }

    fun testLogout() {
        tokenManager.clear()
    }

    fun testExpireToken() {
        val oldToken = tokenManager.get()
        val newToken = oldToken.copy(accessToken = "hehe")
        tokenManager.set(newToken)
    }

    fun getAllBatches() = viewModelScope.launch {
        _uiState.update { it.copy(detail = HomeState.Detail.Loading) }
        batchRepository.getAll()
            .onLeft { result ->
                _uiState.update {
                    it.copy(
                        detail = HomeState.Detail.Success(
                            batches = result
                        )
                    )
                }
            }
            .onRight {
                _uiState.update { it.copy(detail = HomeState.Detail.Failure) }
            }
    }
}