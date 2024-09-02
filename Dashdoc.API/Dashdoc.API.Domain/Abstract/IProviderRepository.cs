using Dashdoc.API.Domain.Entities;

namespace Dashdoc.API.Domain.Abstract;

public interface IProviderRepository
{
    Task<Provider?> GetIdByAsync(long providerId); 
    Task<Provider?> GetByEmailAsync(string providerEmail);
    Task<Provider?> CreateAsync(Provider entityToCreate);
    Task<Provider?> UpdateAsync(Provider entityToUpdate);
    Task<bool> DeleteAsync(long providerId);
    Task<IEnumerable<Provider>> GetAllByAdminIdAsync(long agencyAdminId);
}