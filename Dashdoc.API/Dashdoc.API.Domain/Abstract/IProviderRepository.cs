using Dashdoc.API.Domain.Entities;

namespace Dashdoc.API.Domain.Abstract;

public interface IProviderRepository
{
    Task<Provider?> GetByIdAsync(long providerId); 
    Task<Provider?> GetByEmailAsync(string providerEmail);
    Task<Provider?> CreateAsync(Provider entityToCreate);
    Task<Provider?> UpdateAsync(Provider entityToUpdate);
    Task<bool> DeleteAsync(long providerId);
    List<Provider> GetAllAgencyProvidersAsync(long agencyId);
}