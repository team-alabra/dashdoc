using Dashdoc.API.Domain.Entities;

namespace Dashdoc.API.Domain.Abstract.Services;

public interface IAgencyService
{
    Task<Agency> GetById(long providerId);
    Task<Agency> GetByEmail(string providerEmail);
    Task Create(Agency agency);
    Task Update(Agency agency);
    Task Delete(Agency agency);
}