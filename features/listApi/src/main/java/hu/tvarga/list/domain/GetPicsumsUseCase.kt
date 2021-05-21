package hu.tvarga.list.domain

import androidx.paging.PagingData
import androidx.paging.map
import hu.tvarga.listapi.PicsumRepository
import hu.tvarga.model.PicsumItem
import hu.tvarga.model.toPicsumItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetPicsumsUseCase @Inject constructor(private val repository: PicsumRepository) {

    operator fun invoke(): Flow<PagingData<PicsumItem>> {
        return repository.getPicsums().map { pagingData -> pagingData.map { it.toPicsumItem() } }
    }
}
