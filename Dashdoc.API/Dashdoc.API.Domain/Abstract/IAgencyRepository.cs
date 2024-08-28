using Dashdoc.API.Domain.Entities;

namespace Dashdoc.API.Domain.Abstract;

public interface IAgencyRepository
{
    Task<Agency> GetById(long agencyId);
    Task Create(Agency entityToCreate);
    Task Update(Agency entityToUpdate);
    Task Delete(Agency entityToDelete);
}