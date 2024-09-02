using Dashdoc.API.Domain.Abstract;
using Dashdoc.API.Domain.Entities;

namespace Dashdoc.API.Data.Repositories;

public class ProviderRepository : IProviderRepository
{
    public Task<Provider?> GetIdByAsync(long providerId)
    {
        throw new NotImplementedException();
    }

    public Task<Provider?> GetByEmailAsync(string providerEmail)
    {
        throw new NotImplementedException();
    }

    public Task<Provider?> CreateAsync(Provider entityToCreate)
    {
        throw new NotImplementedException();
    }

    public Task<Provider?> UpdateAsync(Provider entityToUpdate)
    {
        throw new NotImplementedException();
    }

    public Task<bool> DeleteAsync(long providerId)
    {
        throw new NotImplementedException();
    }

    public Task<IEnumerable<Provider>> GetAllByAdminIdAsync(long agencyAdminId)
    {
        throw new NotImplementedException();
    }
}