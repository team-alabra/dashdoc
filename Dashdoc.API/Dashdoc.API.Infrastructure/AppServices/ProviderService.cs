using Dashdoc.API.Domain.Abstract;
using Dashdoc.API.Domain.Abstract.Services;
using Dashdoc.API.Domain.Entities;
using Microsoft.Extensions.Logging;

namespace Dashdoc.API.Infrastructure.AppServices;

public class ProviderService : IProviderService
{
    private readonly IProviderRepository _providerRepository;
    private readonly ILogger<ProviderService> _logger;
  
    public ProviderService(IProviderRepository providerRepository, ILogger<ProviderService> logger)
    {
        _providerRepository = providerRepository;
        _logger = logger;
    }
    public async Task<Provider?> GetById(long providerId)
    {
        try
        {
            var provider = await _providerRepository.GetByIdAsync(providerId);
            return provider;
        }
        catch (Exception ex)
        {
            _logger.LogInformation(ex, "Error retrieving your provider");
            throw;
        }
    }

    public async Task<Provider?> GetByEmail(string providerEmail)
    {
        try
        {
            var provider = await _providerRepository.GetByEmailAsync(providerEmail);
            
            return provider;
        }
        catch (Exception ex)
        {
            _logger.LogInformation(ex, "Error retrieving your provider by email");
            throw;
        }
    }

    public async Task<Provider?> Create(Provider providerToCreate)
    {
        try
        {
           var provider = await _providerRepository.CreateAsync(providerToCreate);
           return provider;
        }
        catch (Exception ex)
        {
            _logger.LogInformation(ex, "Error updating your provider details");
            throw;
        }
    }

    public async Task<Provider?> Update(Provider providerToUpdate)
    {
        try
        {
            var provider = await _providerRepository.UpdateAsync(providerToUpdate);
            return provider;
        }
        catch (Exception ex)
        {
            _logger.LogInformation(ex, "Error updating your provider details");
            throw;
        }
    }
    
    public async Task<bool> Delete(long providerId)
    {
        try
        {
            await _providerRepository.DeleteAsync(providerId);
            return true;
        }
        catch (Exception ex)
        {
            _logger.LogInformation(ex, "Error deleting provider");
            throw;
        }
    }

    public List<Provider> GetAll(long agencyAdminId)
    {
        try
        {
           return _providerRepository.GetAllAgencyProvidersAsync(agencyAdminId);
        }
        catch (Exception ex)
        {
            _logger.LogInformation(ex, "Error updating your provider details");
            throw;
        }
    }
}