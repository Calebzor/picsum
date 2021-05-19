package hu.tvarga.list.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import hu.tvarga.core.resource.Resource
import hu.tvarga.listapi.PicsumRepository
import hu.tvarga.model.PicsumItem
import javax.inject.Inject

class GetPicsumsUseCase @Inject constructor(private val repository: PicsumRepository) {

    suspend operator fun invoke(forceRefresh: Boolean = false, page: Int = 1): LiveData<Resource<List<PicsumItem>>> {
        return Transformations.map(repository.getPicsums(forceRefresh, page)) {
            it // Place here your specific logic actions
        }
    }
}
