package hu.tvarga.list.domain

import androidx.paging.PagingData
import hu.tvarga.listapi.PicsumRepository
import hu.tvarga.model.PicsumItemEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetPicsumsUseCase @Inject constructor(private val repository: PicsumRepository) {

    operator fun invoke(): Flow<PagingData<PicsumItemEntity>> {
        return repository.getPicsums().map {
            it // Place here your specific logic actions
        }
    }
}
