package hu.tvarga.picsum.detail

import hu.tvarga.listapi.PicsumRepository
import hu.tvarga.model.PicsumItem
import hu.tvarga.model.toPicsumItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetPicsumUseCase @Inject constructor(private val repository: PicsumRepository) {

    operator fun invoke(id: String): Flow<PicsumItem> {
        return repository.getPicsum(id).map { it.toPicsumItem() }
    }
}
