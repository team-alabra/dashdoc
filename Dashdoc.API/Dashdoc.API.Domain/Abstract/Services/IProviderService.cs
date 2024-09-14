using Dashdoc.API.Domain.Entities;

namespace Dashdoc.API.Domain.Abstract.Services;

public interface IProviderService
{
    Task<Provider?> GetById(long providerId);
    Task<Provider?> GetByEmail(string providerEmail);
    Task<Provider?> Create(Provider providerToCreate);
    Task<Provider?> Update(Provider providerToUpdate);
    Task<bool> Delete(long providerId);
    List<Provider> GetAll(long agencyId);
}