using Dashdoc.API.Domain.Entities;

namespace Dashdoc.API.Domain.Abstract;

public interface IAgencyRepository
{
    Task<Agency?> GetByIdAsync(long agencyId);
    Task<Agency> CreateAsync(Agency entityToCreate);
    Task<Agency> UpdateAsync(Agency entityToUpdate);
    Task<bool> DeleteAsync(long agencyId);
}